package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Admin extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText title,title22,price,description;
    RadioButton radioButton;
    RadioGroup radioGroup;
    TextView Category,image33;
    Button button;
    private static long i=1;
    private static long l=1;
    Uri imageUrl=null;
    private static final int Gallery=1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        radioGroup = findViewById(R.id.radioGroup);
        Category = findViewById(R.id.category);
        title22 = findViewById(R.id.title);
        price = findViewById(R.id.pricetxt);
        description = findViewById(R.id.description);
        imageButton = findViewById(R.id.imageButton);
        button = findViewById(R.id.button);
        image33=findViewById(R.id.textView13);
        mDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery && resultCode==RESULT_OK){
            imageUrl=data.getData();

            imageButton.setImageURI(imageUrl);
        }
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                //title22.setText(radioButton.getText());
                String title1 = title22.getText().toString();
                mRef=mDatabase.getReference().child("collection").child(String.valueOf(radioButton.getText())).child(title1);

                String price1 = price.getText().toString();
                String description1 = description.getText().toString();
                Category.setText("your choice is " + radioButton.getText());


                if (!(title1.isEmpty() && price1.isEmpty() && description1.isEmpty() && imageUrl != null)) {

                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = FirebaseStorage.getInstance().getReference("images/"+title1);
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();
                                    mRef.child("title").setValue(title1);
                                    mRef.child("price").setValue(price1);
                                    mRef.child("description").setValue(description1);
                                    mRef.child("pic").setValue(task.getResult().toString());

                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
                    i++;
                    l++;
                }
            }
        });

    }

    public void Check(View view){
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this, "selected Category is "+radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

}