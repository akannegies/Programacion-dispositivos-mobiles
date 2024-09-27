package com.example.myapplicationjava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare ArrayList for countries
    private ArrayList<String> paises;

    // Declare EditText for user input
    private EditText editText;

    // Declare adapter globally so it can be accessed inside the listener
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the ArrayList with countries from string array resource
        String[] initialPaises = getResources().getStringArray(R.array.paises);
        paises = new ArrayList<>(Arrays.asList(initialPaises));

        // Initialize EditText
        editText = findViewById(R.id.editText1);

        // Initialize ArrayAdapter with the ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paises);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // Set an item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected item
                String selectedItem = paises.get(position);
                Toast.makeText(MainActivity.this, "Has seleccionado el país: " + selectedItem, Toast.LENGTH_SHORT).show();

                // Remove the selected item from the list
                paises.remove(position);

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }
        });

        // Set a click listener on the button to add inputted text to the list
        findViewById(R.id.main_button_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from EditText
                String newPais = editText.getText().toString();

                // Check if input is not empty
                if (!newPais.isEmpty()) {
                    // Add the new text to the ArrayList
                    paises.add(newPais);

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();

                    // Clear the EditText field after adding the text
                    editText.setText("");

                    // Show a Toast indicating the item was added
                    Toast.makeText(MainActivity.this, "Has añadido el país: " + newPais, Toast.LENGTH_SHORT).show();
                } else {
                    // Show a Toast if input is empty
                    Toast.makeText(MainActivity.this, "Por favor, ingresa un país válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




