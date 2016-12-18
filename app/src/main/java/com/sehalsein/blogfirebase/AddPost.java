package com.sehalsein.blogfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPost extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference postReference = database.getReference("post");

    EditText title,author,content;
    Button post;
    Post postObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        title = (EditText) findViewById(R.id.title_editText);
        author = (EditText) findViewById(R.id.author_editText);
        content = (EditText) findViewById(R.id.content_editText);

        post = (Button) findViewById(R.id.post_button);

        postObject = new Post();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postObject.setTitle(title.getText().toString());
                postObject.setAuthor(author.getText().toString());
                postObject.setContent(content.getText().toString());

                String key = postReference.push().getKey();

                postReference.child(key).setValue(postObject);

                Toast.makeText(AddPost.this, "Your post is added to the blog.", Toast.LENGTH_SHORT).show();
                AddPost.this.finish();
            }
        });

    }
}
