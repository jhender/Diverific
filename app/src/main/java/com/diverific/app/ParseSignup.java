package com.diverific.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Locale;

public class ParseSignup extends Activity implements OnClickListener {

	private EditText mUserNameEditText;
	private EditText mEmailEditText;
	private EditText mWhereEditText;
	private EditText mConfirmPasswordEditText;
	private Button mCreateAccountButton;

	private String mEmail;
	private String mUsername;
	private String mPassword;
	private String mConfirmPassword;
    private String mWhere;
	
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	//ConnectionDetector cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_signup);

		// creating connection detector class instance
		//cd = new ConnectionDetector(getApplicationContext());

		mUserNameEditText = (EditText) findViewById(R.id.etUsername);
		mEmailEditText = (EditText) findViewById(R.id.etEmail);
		mWhereEditText = (EditText) findViewById(R.id.etWhere);
//		mConfirmPasswordEditText = (EditText) findViewById(R.id.etPasswordConfirm);
		
		mCreateAccountButton = (Button) findViewById(R.id.btnCreateAccount);
		mCreateAccountButton.setOnClickListener(this);
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		//getMenuInflater().inflate(R.menu.parse_signup, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCreateAccount:
			// get Internet status
			//isInternetPresent = cd.isConnectingToInternet();
			// check for Internet status
//			if (isInternetPresent) {
				// Internet Connection is Present
				// make HTTP requests
				//createAccount();
//			} else {
//				// Internet connection is not present
//				// Ask user to connect to Internet
//				showAlertDialog(getApplicationContext(), "No Internet Connection",
//						"You don't have internet connection.", false);
//			}

            mEmail = mEmailEditText.getText().toString();
            mUsername = mUserNameEditText.getText().toString();
            mWhere = mWhereEditText.getText().toString();

            ParseObject signUp = new ParseObject("SignUp");
            signUp.put("Name", mUsername);
            signUp.put("Email", mEmail);
            signUp.put("Where", mWhere);

            signUp.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        signUpMsg("We'll be in contact soon!");
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        signUpMsg("Sorry there was some problem.");
                    }
                }
            });

			
			break;

		default:
			break;
		}
	}
	
	private void createAccount(){
		clearErrors();

		boolean cancel = false;
		View focusView = null;

		// Store values at the time of the login attempt.
		mEmail = mEmailEditText.getText().toString();
		mUsername = mUserNameEditText.getText().toString();
//		mPassword = mPasswordEditText.getText().toString();
		mConfirmPassword = mConfirmPasswordEditText.getText().toString();
        mWhere = mWhereEditText.getText().toString();

//		// Check for a valid confirm password.
//		if (TextUtils.isEmpty(mConfirmPassword)) {
//			mConfirmPasswordEditText.setError(getString(R.string.error_field_required));
//			focusView = mConfirmPasswordEditText;
//			cancel = true;
//		} else if (mPassword != null && !mConfirmPassword.equals(mPassword)) {
//			mPasswordEditText.setError(getString(R.string.error_invalid_confirm_password));
//			focusView = mPasswordEditText;
//			cancel = true;
//		}
//		// Check for a valid password.
//		if (TextUtils.isEmpty(mPassword)) {
//			mPasswordEditText.setError(getString(R.string.error_field_required));
//			focusView = mPasswordEditText;
//			cancel = true;
//		} else if (mPassword.length() < 4) {
//			mPasswordEditText.setError(getString(R.string.error_invalid_password));
//			focusView = mPasswordEditText;
//			cancel = true;
//		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailEditText.setError(getString(R.string.error_field_required));
			focusView = mEmailEditText;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailEditText.setError(getString(R.string.error_invalid_email));
			focusView = mEmailEditText;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			Toast.makeText(getApplicationContext(), "signUp", Toast.LENGTH_SHORT).show();
			signUp(mUsername.toLowerCase(Locale.getDefault()), mEmail, mPassword);

		}

	}

	private void signUp(final String mUsername, String mEmail, String mPassword) {
//		Toast.makeText(getApplicationContext(), mUsername + " - " + mEmail, Toast.LENGTH_SHORT).show();
//		ParseUser user = new ParseUser();
//		user.setUsername(mUsername);
//		user.setPassword(mPassword);
//		user.setEmail(mEmail);

        ParseObject signUp = new ParseObject("SignUp");
        signUp.put("Name", mUsername);
        signUp.put("Email", mEmail);
        signUp.put("Where", mWhere);
		 
		signUp.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    //signUpMsg("We'll be in contact soon!");
//                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(in);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    signUpMsg("Sorry there was some problem.");
                }
            }
        });
//
//        Intent in = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(in);
//        finish();
	}

	protected void signUpMsg(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void clearErrors(){ 
		//mEmailEditText.setError(null);
		//mUserNameEditText.setError(null);
//		mPasswordEditText.setError(null);
		//mConfirmPasswordEditText.setError(null);
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	
	
	

}
