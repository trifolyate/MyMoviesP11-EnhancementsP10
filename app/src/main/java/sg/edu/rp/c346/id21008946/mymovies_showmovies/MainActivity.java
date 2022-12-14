package sg.edu.rp.c346.id21008946.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear, tvRating;
    EditText etTitle, etGenre, etYear;
    Spinner spnRating;
    Button btnInsert, btnShowList;
    String movieRating;
//    ListView lvTest;
    ArrayList<Movies> alMovieList;
//    CustomAdapter caMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear =findViewById(R.id.tvYear);
        tvRating =findViewById(R.id.tvRating);
        etTitle =findViewById(R.id.etTitle);
        etGenre =findViewById(R.id.etGenre);
        etYear =findViewById(R.id.etYear);
        spnRating =findViewById(R.id.spnRating);
        btnInsert =findViewById(R.id.btnInsert);
        btnShowList =findViewById(R.id.btnShowList);

        //test
//        lvTest = findViewById(R.id.listViewTest);
        alMovieList = new ArrayList<>();
//        caMovie = new CustomAdapter(this,R.layout.row,alMovieList);
//        lvTest.setAdapter(caMovie);

        populateData();


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Show_Movies.class);
                startActivity(i);
            }});

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movietitle = etTitle.getText().toString();
                String moviegenre = etGenre.getText().toString();
                String yearString = etYear.getText().toString();
                int year = Integer.parseInt(yearString);
                String movieratingfinal = movieRating + "";
                if(year < 1888)
                {
                    Toast.makeText(MainActivity.this,"Please enter the correct year(more than 1888)",Toast.LENGTH_LONG).show();
                }else if(year > 2022)
                {
                    Toast.makeText(MainActivity.this,"Please enter the correct year(less than 2022)",Toast.LENGTH_LONG).show();
                }else
                {
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id =dbh.insertMovie(movietitle,moviegenre,year,movieratingfinal);

                    if (inserted_id != -1) {
                        alMovieList.clear();
                        alMovieList.addAll(dbh.getAllMovies());
//                    caMovie.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Insert not successful",
                                Toast.LENGTH_SHORT).show();
                    }
                    etTitle.setText("");
                    etGenre.setText("");
                    etYear.setText("");
                    etYear.setSelection(0);
                }
            }
        });

        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String spinnerItems1 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems1;
                        break;
                    case 1:
                        String spinnerItems2 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems2;
                        break;
                    case 2:
                        String spinnerItems3 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems3;
                        break;
                    case 3:
                        String spinnerItems4 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems4;
                        break;
                    case 4:
                        String spinnerItems5 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems5;
                        break;
                    case 5:
                        String spinnerItems6 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems6;
                        break;
                    case 6:
                        String spinnerItems7 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems7;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etTitle.addTextChangedListener(songInsertTextWatcher);
        etGenre.addTextChangedListener(songInsertTextWatcher);
        etYear.addTextChangedListener(songInsertTextWatcher);
    }

    private TextWatcher songInsertTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String title = etTitle.getText().toString().trim();
            String singers = etGenre.getText().toString().trim();
            String year = etYear.getText().toString().trim();

            if(!title.isEmpty() && !singers.isEmpty() && !year.isEmpty() && year.length()==4)
            {
                btnInsert.setEnabled(true);
            }
            else
            {
                btnInsert.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    void populateData(){
        DBHelper dbh = new DBHelper(MainActivity.this);
        Movies item1 = new Movies(1,"Title","Genre",2111,"Nice");
        alMovieList.add(item1);
//        alMovieList.clear();
//        alMovieList.addAll(dbh.getAllMovies());
//        caMovie.notifyDataSetChanged();
    }


}
