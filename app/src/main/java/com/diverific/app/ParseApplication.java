package com.diverific.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

        /*
		 * In this tutorial, we'll subclass ParseObject for convenience to
		 * create and modify Meal objects
		 */

		// Add your initialization code here
	    Parse.initialize(this, "H43u08age9NuU2KuJ2O4lBnEFeCaD7IPu8Tav5WF", "RL5XW3UOfAoecHdWIgQjG27wEp5mwB0gmfhum9to");

        ParseObject.registerSubclass(Dive.class);

        //Automatic user interferes with getCurrentUser and currentUser == null
		//ParseUser.enableAutomaticUser();
		
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);


	}
	
	
	
	
	

}
