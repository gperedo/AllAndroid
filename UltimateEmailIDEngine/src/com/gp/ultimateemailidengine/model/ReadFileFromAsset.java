package com.gp.ultimateemailidengine.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class ReadFileFromAsset {
	Context context;
    String fileName;
    
    public ReadFileFromAsset(){}
    
    //Pass parameters: getApplicationContext() and filename from assests folder
    //use context data type because class is not bundled by android activity
    public ReadFileFromAsset(Context myContext, String fileName){
            this.context = myContext;
            this.fileName = fileName;
    }
    
    public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<String> readFile(){
    	
            BufferedReader br = null;
            String sCurrentLine = null;
            ArrayList<String> al = new ArrayList<String>();
            AssetManager mngr = context.getAssets();
            
            try {
                    br = new BufferedReader(new InputStreamReader(mngr.open(fileName)));
                    
                    while ((sCurrentLine = br.readLine()) != null) {
                            al.add(sCurrentLine.trim());
                    }
                    
            } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Error:", fileName+": Cannot read the file.");
            } finally{
                    try {
                            if (br != null)br.close();
                    } catch (IOException ex) {
                            ex.printStackTrace();
                            Log.e("Error:", "Cannot close the file.");
                    }
            }
            
            return al;
    }
}
