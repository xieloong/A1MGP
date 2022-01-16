package com.example.week4real;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;


public class scorepage extends Activity implements OnClickListener {
    private Button btn_sharescore;
    private Button btn_back;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    private static final String EMAIL = "email";
    private LoginButton btn_fbLogin;
    private int highscore;
    private ShareDialog share_Dialog;
    private int PICK_IMAGE_REQUEST = 1;

    // ShareDialog shareDialog
    ProfilePictureView profile_pic;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide Title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Week 14 - Initialise for FB
        FacebookSdk.setApplicationId("247843774159476");
        FacebookSdk.isInitialized();

        if(BuildConfig.DEBUG)
        {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        setContentView(R.layout.scorepage);

        TextView scoreText;
        scoreText = (TextView) findViewById(R.id.scoreText);

        Typeface myfont;
        myfont = Typeface.createFromAsset(getAssets(),"fonts/Gemcut.otf");
        if(GameSystem.Instance.playerNames.size() > 1)
        {
            String playerName = GameSystem.Instance.playerNames.get(GameSystem.Instance.playerNames.size() - 1);
            highscore = GameSystem.Instance.GetIntFromSave(playerName);
            scoreText.setText(String.format(playerName +"'s highscore is " + highscore));
        }
        else
        {
            scoreText.setText(String.format("Player" +"'s highscore is " + 0));
        }



        // Define for back button
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        // Define for Scoreshare button
        btn_sharescore = (Button)findViewById(R.id.btn_sharescore);
        btn_sharescore.setOnClickListener(this);

        // Define fb button
        btn_fbLogin = (LoginButton)findViewById(R.id.fb_login_button);
        btn_fbLogin.setReadPermissions(Arrays.asList(EMAIL));
        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile","email"));

        callbackManager = CallbackManager.Factory.create();

        loginManager = LoginManager.getInstance();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profile_pic.setProfileId(Profile.getCurrentProfile().getId());

                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                loginResult.getAccessToken().getUserId();
            }

            @Override
            public void onCancel() {
                System.out.println("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("Login attempt failed.");
            }
        });


    }

    public void shareScore(){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        if(ShareDialog.canShow(SharePhotoContent.class)){
            System.out.println("photoShown.");
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Thank You for playing The World is Dying. Your Final Score is " + highscore)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            share_Dialog.show(content);


        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        if (v == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
        }
        else if(v == btn_sharescore)
        {
            shareScore();
        }
        startActivity(intent);
    }

    public void OnActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
