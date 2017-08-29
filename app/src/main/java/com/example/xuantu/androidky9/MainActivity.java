package com.example.xuantu.androidky9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch, edtName, edtSkill;
    RadioGroup radioSex;
    ListView lvListStudent;
    Button btnAdd, btnEdit;
    ArrayList<Student> listStudent = new ArrayList<>();
    CustomListStudent adapterStudent;
    String sex;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        edtName = (EditText) findViewById(R.id.edtName);
        edtSkill = (EditText) findViewById(R.id.edtSkill);
        radioSex = (RadioGroup) findViewById(R.id.radioSex);
        lvListStudent = (ListView) findViewById(R.id.lvListStudent);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        listStudent.add(new Student(
                "Phạm Xuân Tú",
                "Nam",
                "react native, js, php"
        ));
        listStudent.add(new Student(
                "Trần Văn Cương",
                "Nam",
                "java, android"
        ));
        listStudent.add(new Student(
                "Dương Xuân Tùng",
                "Nam",
                "swift, iOS"
        ));
        listStudent.add(new Student(
                "Chu Xuân Linh",
                "Nam",
                "game, unity"
        ));

        adapterStudent = new CustomListStudent(MainActivity.this, listStudent);
        lvListStudent.setAdapter(adapterStudent);

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnAdd.setEnabled(true);
                if(edtName.getText().toString().equals("") || btnEdit.isEnabled()){
                    btnAdd.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.radioMale){
                    sex = "Nam";
                }else {
                    sex = "Nữ";
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listStudent.add(0, new Student(
                        edtName.getText().toString(),
                        sex,
                        edtSkill.getText().toString()
                ));
                adapterStudent.notifyDataSetChanged();
                edtName.setText("");
                radioSex.clearCheck();
                edtSkill.setText("");
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapterStudent.getFilter().filter(charSequence);
                adapterStudent.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lvListStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                btnEdit.setEnabled(true);
                Student student = (Student) ((CustomListStudent ) adapterView.getAdapter()).getItem(i);
                edtName.setText(student.getName());
                if(student.getSex() == "Nam"){
                    radioSex.check(R.id.radioMale);
                }else {
                    radioSex.check(R.id.radioFemale);
                }
                edtSkill.setText(student.getSkill());

//                position = i;
                for(int j = 0; j < listStudent.size(); j++){
                    if(student.getName().equals(listStudent.get(j).getName())){
                        position = j;
                        break;
                    }
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listStudent.get(position).setName(edtName.getText().toString());
                String sex2;
                if(radioSex.getCheckedRadioButtonId() == R.id.radioMale){
                    sex2 = "Nam";
                }else {
                    sex2 = "Nữ";
                }
                listStudent.get(position).setSex(sex2);
                listStudent.get(position).setSkill(edtSkill.getText().toString());
                if(!edtSearch.getText().toString().equals("")){
                    adapterStudent.filter.filter(edtSearch.getText().toString());
                }
                adapterStudent.notifyDataSetChanged();
                btnEdit.setEnabled(false);
                edtName.setText("");
                radioSex.clearCheck();
                edtSkill.setText("");
            }
        });
    }
}
