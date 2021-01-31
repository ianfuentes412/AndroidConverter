package com.example.assign2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] lengthChoices={"Metre => Foot","Foot => Metre", "Inch => Centimeter", "Centimeter => Inch"};
    String[] weightChoices={"Kilogram => Pound","Pound => Kilogram","Gram => Ounce", "Ounce => Gram"};
    String[] tempChoices = {"Fahrenheit => Celsius", "Celsius => Fahrenheit","Celsius => Kelvin","Fahrenheit => Kelvin","Kelvin => Celsius","Kelvin => Fahrenheit"};
    String[] convertChoices = {"Length","Weight","Temperature"};
    Button convertButton;
    String subPick = "";
    String mainPick = "";
    String suffix = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertButton = (Button) findViewById(R.id.convertBtn);
        EditText InputText = (EditText)findViewById(R.id.InputText);
        TextView OutputText = (TextView) findViewById(R.id.OutputText);
        Spinner subSpinner = (Spinner) findViewById(R.id.spinner2);
        Spinner mainSpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapterTemp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tempChoices);
        ArrayAdapter<String> dataAdapterLength = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,lengthChoices);
        ArrayAdapter<String>dataAdapterWeight=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,weightChoices);
        ArrayAdapter<String> dataAdaptMain = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,convertChoices);
        subSpinner.setAdapter(dataAdapterTemp);
        mainSpinner.setAdapter(dataAdaptMain);
        dataAdapterTemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "You Selected " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                switch (position)
                {
                    case 0:
                        subSpinner.setAdapter(dataAdapterLength);
                        dataAdapterLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mainPick = "length";
                        break;

                    case 1:
                        subSpinner.setAdapter(dataAdapterWeight);
                        dataAdapterWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mainPick = "weight";
                        break;

                    case 2:
                        subSpinner.setAdapter(dataAdapterTemp);
                        dataAdapterTemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mainPick = "temp";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                subSpinner.setAdapter(dataAdapterLength);
                dataAdapterLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }
        });


        subSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(mainPick)
                {
                    case "temp":
                        switch(position)
                        {
                            case 0:
                                subPick = "F-C";
                                break;

                            case 1:
                                subPick = "C-F";
                                break;

                            case 2:
                                subPick = "C-K";
                                break;

                            case 3:
                                subPick = "F-K";
                                break;

                            case 4:
                                subPick = "K-C";
                                break;

                            case 5:
                                subPick = "K-F";
                                break;
                        }
                        break;

                    case "weight":
                        switch(position)
                        {
                            case 0:
                                subPick = "Kg-Lb";
                                break;

                            case 1:
                                subPick = "Lb-Kg";
                                break;

                            case 2:
                                subPick = "g-Oz";
                                break;

                            case 3:
                                subPick = "Oz-g";
                                break;
                        }
                        break;

                    case "length":
                        switch (position)
                        {
                            case 0:
                                subPick = "M-Ft";
                                break;

                            case 1:
                                subPick = "Ft-M";
                                break;

                            case 2:
                                subPick = "I-Cm";
                                break;

                            case 3:
                                subPick = "Cm-I";
                                break;
                        }

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convertButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String text = InputText.getText().toString();
                Double convert = Double.parseDouble(text);
                Double result = 0.0;

                switch(subPick)
                {
                    case "F-C":
                        result = convert * 2 + 30;
                        suffix = "C*";
                        break;
                    case "C-F":
                        result = (convert - 30) / 2;
                        suffix = "F*";
                        break;
                    case "C-K":
                        result = convert + 273.15;
                        suffix = "K*";
                        break;
                    case "F-K":
                        result = (((convert - 32) * 5) / 9) + 273.15;
                        suffix = "K*";
                        break;
                    case "K-C":
                        result = convert - 273.15;
                        suffix = "C*";
                        break;
                    case "K-F":
                        result = (((convert - 273.15) * 9) / 5) + 32;
                        suffix = "F*";
                        break;

                    case "Kg-Lb":
                        result = convert * 2.2046;
                        suffix = "Lb";
                        if (result < 0)
                    {
                        result = 0.0;
                    }

                        break;
                    case "Lb-Kg":
                        result = convert / 2.2046;
                        suffix = "Kg";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;
                    case "g-Oz":
                        result = convert * 0.035274;
                        suffix = "Oz";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;
                    case "Oz-g":
                        result = convert / 0.035272;
                        suffix = "g";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;

                    case "M-Ft":
                        result = convert / 0.3048;
                        suffix = "Ft";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;
                    case "Ft-M":
                        result = convert * 0.3048;
                        suffix  = "m";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;
                    case "I-Cm":
                        suffix = "cm";
                        result = convert * 2.54;
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;
                    case "Cm-I":
                        result = convert / 2.54;
                        suffix = "in";
                        if (result < 0)
                        {
                            result = 0.0;
                        }
                        break;

                }
                OutputText.setText(result.toString() + " " + suffix);


            }
        });
    }
}