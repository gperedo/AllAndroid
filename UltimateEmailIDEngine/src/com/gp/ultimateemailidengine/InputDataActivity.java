package com.gp.ultimateemailidengine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.gp.ultimateemailidengine.model.DomainFormat;
import com.gp.ultimateemailidengine.model.ReadFileFromAsset;
import com.gp.ultimateemailidengine.model.RenameDialogFragment;
import com.gp.ultimateemailidengine.model.WriteFileFromStorage;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InputDataActivity extends FragmentActivity implements RenameDialogFragment.NoticeDialogListener {
	
	Spinner fname_;
	Spinner sname_;
	EditText separator_;
	EditText dname_;
	EditText beforeNameKeyword_;
	EditText afterNameKeyword_;
	
	String fname;
	String sname;
	String separator;
	String dname;
	String beforeNameKeyword;
	String afterNameKeyword;
	
	DialogFragment renameFile = new RenameDialogFragment();
	
	Date Date = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_hhmmssa");
	String nowDate = sf.format(Date).toString();
	
	String folderName = "AEmailGenerator";
	String fileName = "aegen"+nowDate+".txt";
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getBeforeNameKeyword() {
		return beforeNameKeyword;
	}

	public void setBeforeNameKeyword(String beforeNameKeyword) {
		this.beforeNameKeyword = beforeNameKeyword;
	}

	public String getAfterNameKeyword() {
		return afterNameKeyword;
	}

	public void setAfterNameKeyword(String afterNameKeyword) {
		this.afterNameKeyword = afterNameKeyword;
	}

	DomainFormat checkDomain = new DomainFormat();
	
	public List<String> getListNames(){
		List<String> listNames = new ArrayList<String>();
		
		listNames.add("US - First Name");
		listNames.add("US - Surname");
		listNames.add("Philippines - First Name");
		listNames.add("Philippines - Surname");
		listNames.add("UK - First Name");
		listNames.add("UK - Surname");
		listNames.add("India - First Name");
		listNames.add("India - Surname");
		listNames.add("Keyword");
		
		return listNames;
	}
	
	/**
	 * Method that Maps the return value of the fields. 
	 * **/
	public String getValueFromHashMap(String value){
				String textFileValue = null;
		
				//Using Hashmap
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("Keyword", "keyword");
				hm.put("US - First Name", "fname-us.txt");
				hm.put("US - Surname", "lname-us.txt");
				hm.put("Philippines - First Name", "fname-phil.txt");
				hm.put("Philippines - Surname", "lname-phil.txt");
				hm.put("UK - First Name", "fname-uk.txt");
				hm.put("UK - Surname", "lname-uk.txt");
				hm.put("India - First Name", "fname-inda.txt");
				hm.put("India - Surname", "lname-inda.txt");
				
				textFileValue = hm.get(value);
		return textFileValue;
	}
	
	public ArrayList<String> concatFiles(ArrayList<String> first, ArrayList<String> second){
		
		ArrayList<String> al = new ArrayList<String>();
		
		for(int i = 0; i < first.size(); i++){
			for(int j = 0; j < second.size(); j++){
				al.add(getBeforeNameKeyword()+first.get(i)+getSeparator()+second.get(j)+getAfterNameKeyword()+getDname());
			}
		}
		
		return al;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputdata_activity);
		
		fname_ = (Spinner)findViewById(R.id.firstnames_spinner);
		sname_ = (Spinner)findViewById(R.id.surnames_spinner);
		separator_ = (EditText)findViewById(R.id.separator_edittext);
		dname_ = (EditText)findViewById(R.id.domainname_editText);
		beforeNameKeyword_ = (EditText)findViewById(R.id.beforenameKeyword_editText);
		afterNameKeyword_ = (EditText)findViewById(R.id.afternameKeyword_editText);
		
		Button pass_btn = (Button)findViewById(R.id.pass_btn);
		
		ArrayAdapter<String> adapter_fname = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getListNames());
		adapter_fname.setDropDownViewResource(android.R.layout.simple_spinner_item);
		fname_.setAdapter(adapter_fname);
		
		ArrayAdapter<String> adapter_sname = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getListNames());
		adapter_sname.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sname_.setAdapter(adapter_sname);
		
		
		
		pass_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				setFname(getValueFromHashMap(fname_.getSelectedItem().toString()));
				setSname(getValueFromHashMap(sname_.getSelectedItem().toString()));
				setSeparator(separator_.getText().toString().trim());
				setDname(dname_.getText().toString().trim());
				setBeforeNameKeyword(beforeNameKeyword_.getText().toString().trim());
				setAfterNameKeyword(afterNameKeyword_.getText().toString().trim());
				
				Boolean VALIDATE = true;
				
				ReadFileFromAsset firstFile = new ReadFileFromAsset(getApplicationContext(), getFname());
				ReadFileFromAsset secondFile = new ReadFileFromAsset(getApplicationContext(), getSname());
				WriteFileFromStorage fileWriter = new WriteFileFromStorage();
				
				fileWriter.setDirName(folderName);
				fileWriter.setFileName(fileName);
				
				Log.i("info:fname",getFname());
				Log.i("info:sname",getSname());
				Log.i("info:separator",getSeparator());
				Log.i("info:domain",getDname());
				Log.i("info:before key",getBeforeNameKeyword());
				Log.i("info:after key",getAfterNameKeyword());
				
				if(getDname().equals("")){
					Toast.makeText(getApplicationContext(), "Email Service Name field is require!", Toast.LENGTH_LONG).show();
					VALIDATE = false;
				}
				
				if(!checkDomain.validate(getDname()) && !getDname().isEmpty()){
					Toast.makeText(getApplicationContext(), "Invalid Email Service Name!", Toast.LENGTH_LONG).show();
					VALIDATE = false;
				}
				
				if(VALIDATE)
					renameFile.show(getFragmentManager(), "missiles");
				
				/**
				 * 
				if(VALIDATE){
					ArrayList<String> concatFiles;
					concatFiles = concatFiles(firstFile.readFile(), secondFile.readFile());
					
					if(!concatFiles.isEmpty()){
						 fileWriter.createFileFolder();
						 fileWriter.writeToAFile(concatFiles);
						 
						 Toast.makeText(getApplicationContext(), concatFiles.size()+" Emails is Generated.", Toast.LENGTH_LONG).show();
						 Log.i("info:", concatFiles.size()+" emails created.");
					 }else{
						 Toast.makeText(getApplicationContext(), "ERROR: No Data Found!", Toast.LENGTH_LONG).show();
						 Log.i("info:", "There is no data to generate.");
					 }
				}
				*/
				//go back to main menu
				//Intent intent = new Intent(this, YourMainActivity.class);
		        //startActivity(intent);
		        //finish();
				
			}
		});
	
	}

	@Override
	public void onDialogPositiveClick(String rename) {
		
		Log.i("inform dialog", rename);
		
	}

	
}
