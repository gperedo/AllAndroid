package com.gp.ultimateemailidengine.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

public class WriteFileFromStorage {
	String dirName;
    String fileName;
    
    StorageHelper storageHelper = new StorageHelper();
    
    public WriteFileFromStorage(){}
    

    public String getDirName() {
            return dirName;
    }

    public void setDirName(String dirName) {
            this.dirName = dirName;
    }

    public String getFileName() {
            return fileName;
    }

    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    
    public void createFileFolder(){
    	
    	File folderPath = new File(Environment.getExternalStorageDirectory() +File.separator+getDirName());
    	File filePath = new File(Environment.getExternalStorageDirectory() +"/"+getDirName()+"/"+getFileName());
    	
    	if(storageHelper.isExternalStorageAvailable() && storageHelper.isExternalStorageAvailableAndWriteable() && storageHelper.isExternalStorageWriteable()){
    		
    		if(!folderPath.exists()){
        		Log.i("Info:", getDirName()+" is Created.");
        		folderPath.mkdirs();
        	}
        	
        	if(!filePath.exists()){
        		try {
        			Log.i("Info:", getFileName()+" is Created.");
        			filePath.createNewFile();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
    	}else{
    		Log.i("info:", "storage not available");
    	}
    	
    	
    	
    	
    }
    
    /**
     * write file to:
     * Environment.getExternalStorageDirectory() or
     * getFilesDir()
     * **/
    public void writeToAFile(ArrayList<String> data){
            try {
                    FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory()+File.separator+dirName+File.separator+fileName);
                    BufferedWriter bw = new BufferedWriter(fw);

                    for(int i = 0; i < data.size(); i++){
						bw.write(data.get(i));
						bw.write("\n");
					}
                    
                    bw.close();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }

}
