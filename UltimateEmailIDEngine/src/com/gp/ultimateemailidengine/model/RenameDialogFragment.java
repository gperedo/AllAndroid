package com.gp.ultimateemailidengine.model;

import com.gp.ultimateemailidengine.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class RenameDialogFragment extends DialogFragment{
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(String rename);
    }
    
    NoticeDialogListener mListener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            
            mListener = (NoticeDialogListener) activity;
            
        } catch (ClassCastException e) {      
        	
        	throw new ClassCastException(activity.toString());
        }
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        View view = inflater.inflate(R.layout.rename_activity, null);
        
        final EditText rename = (EditText)view.findViewById(R.id.rename_field);
        
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        
        builder.setMessage("Set FileName:");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        		   @Override
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogPositiveClick(rename.getText().toString());
                       // FIRE ZE MISSILES!
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
