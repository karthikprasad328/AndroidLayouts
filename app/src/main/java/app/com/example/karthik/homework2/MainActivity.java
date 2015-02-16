package app.com.example.karthik.homework2;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final SupportActionBar bar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffff6700));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(R.id.selection_home))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, PlaceholderFragment.newInstance(id)).commit();
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        /* Returns a new instance of the fragment for the given id*/

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment placeholderFragment= new PlaceholderFragment();
            Bundle args= new Bundle();
            args.putInt("section number", sectionNumber);
            placeholderFragment.setArguments(args);
            return placeholderFragment;
        }

        //Create the View
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView=null ;//= inflater.inflate(R.layout.fragment_main, container, false);
            //get the argument to create the specific fragment
            int options = getArguments().getInt("section number");

            switch (options) {
                case R.id.selection_home:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    TextView text=(TextView) rootView.findViewById(R.id.name);
                    text.setTextSize(getResources().getDimension(R.dimen.textsize));
                    TextView text2=(TextView) rootView.findViewById(R.id.session);
                    text2.setTextSize(getResources().getDimension(R.dimen.textsize));
                    //return rootView;
                    break;

                case R.id.selection_linear:
                    rootView = inflater.inflate(R.layout.linear_buttons, container, false);
                    //return rootView;
                    break;
                case R.id.selection_relative:
                    rootView=inflater.inflate(R.layout.relative_layout, container, false);
                    break;


                case R.id.selection_grid:
                    rootView=inflater.inflate(R.layout.grid_layout, container, false);
                    break;

                case R.id.selection_seekbar:
                    rootView=inflater.inflate(R.layout.seekbar_layout, container, false);
                    TextView text3=(TextView) rootView.findViewById(R.id.seektext);
                    text3.setTextSize(getResources().getDimension(R.dimen.textsize));
                    final SeekBar seekBar=(SeekBar) rootView.findViewById(R.id.seekbar);
                    final ImageButton imageBtn=(ImageButton) rootView.findViewById(R.id.imagefist);

                    imageBtn.setOnLongClickListener(new View.OnLongClickListener(){
                        public boolean onLongClick(View view){
                        seekBar.setProgress(50);
                        return true;
                        }
                    });

                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                    {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                            ViewGroup.LayoutParams params=imageBtn.getLayoutParams();
                            params.width=progress*7;
                            params.height=progress*7;
                            imageBtn.setLayoutParams(params);
                        }
                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar){

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar){

                        }
                    });
                    break;
                case R.id.selection_moviedata:
                    rootView = inflater.inflate(R.layout.moviedata_layout, container, false);

                    int position=0;
                    //get references to each of the views in rootview
                    final TextView moviename=(TextView) rootView.findViewById(R.id.title);
                    final ImageView imageView=(ImageView)rootView.findViewById(R.id.image);
                    final RatingBar ratingBar=(RatingBar)rootView.findViewById(R.id.rating);
                    final TextView description=(TextView) rootView.findViewById(R.id.description);
                    final TextView year=(TextView) rootView.findViewById(R.id.year);
                    final TextView length=(TextView) rootView.findViewById(R.id.length);
                    final TextView stars=(TextView) rootView.findViewById(R.id.stars);
                    final TextView director=(TextView) rootView.findViewById(R.id.director);
                    final TextView url=(TextView) rootView.findViewById(R.id.url);
                    final Button button=(Button)rootView.findViewById(R.id.loadbutton);
                    final Button button2=(Button)rootView.findViewById(R.id.resetbutton);

                    //create instance of movie data
                    final MovieData movieData=new MovieData();
                    //movieData.getItem(0);
                    HashMap item= movieData.getItem(position);

                    //set values to all the views for display
                    String desc=(String)item.get("name");
                    moviename.setText(desc);
                    moviename.setTextSize(20);

                    imageView.setImageResource((int)item.get("image"));


                    float rating=Float.parseFloat(String.valueOf(item.get("rating")));
                    ratingBar.setRating(rating/2);

                    String descriptiontext="Description: "+(String)item.get("description");
                    description.setText(descriptiontext);

                    String yearno="Year: "+(String)item.get("year");
                    year.setText(yearno);

                    String starstext="Stars: "+(String)item.get("stars");
                    stars.setText(starstext);

                    String lengthtext="Length: "+(String)item.get("length");
                    length.setText(lengthtext);

                    String directortext="Director: "+(String)item.get("director");
                    director.setText(directortext);

                    String urltext="URL: " +(String)item.get("url");
                    url.setText(urltext);

                    //Custom button click listener for "Load Next Movie Button"
                    class ButtonClick implements View.OnClickListener{

                        int pos;
                        public ButtonClick(int position){
                            this.pos=position;
                        }
                        @Override
                        public void onClick(View v){
                            pos++;
                            if(pos>29) pos=0;
                            HashMap item= movieData.getItem(pos);

                            //set values to all the views for display
                            String desc=(String)item.get("name");
                            moviename.setText(desc);
                            moviename.setTextSize(20);

                            imageView.setImageResource((int)item.get("image"));

                            float rating=Float.parseFloat(String.valueOf(item.get("rating")));
                            ratingBar.setRating(rating/2);

                            String descriptiontext="Description: "+(String)item.get("description");
                            description.setText(descriptiontext);

                            String yearno="Year: "+(String)item.get("year");
                            year.setText(yearno);

                            String starstext="Stars: "+(String)item.get("stars");
                            stars.setText(starstext);

                            String lengthtext="Length: "+(String)item.get("length");
                            length.setText(lengthtext);

                            String directortext="Director: "+(String)item.get("director");
                            director.setText(directortext);

                            String urltext="URL: " +(String)item.get("url");
                            url.setText(urltext);
                        }

                    }
                    //Set the listener
                    button.setOnClickListener(new ButtonClick(position));


                    //Custom Buttom Click Listener for "Reset Movies" button
                    class RandomClick implements View.OnClickListener{

                        int pos;
                        public RandomClick(int position){
                            this.pos=position;
                        }
                        @Override
                        public void onClick(View v){
                            Random rand=new Random();
                            pos=rand.nextInt(30)+0;
                            if(pos>29) pos=0;

                            HashMap item= movieData.getItem(pos);

                            //set values to all the views for display
                            String desc=(String)item.get("name");
                            moviename.setText(desc);
                            moviename.setTextSize(20);

                            imageView.setImageResource((int)item.get("image"));

                            float rating=Float.parseFloat(String.valueOf(item.get("rating")));
                            ratingBar.setRating(rating/2);

                            String descriptiontext="Description: "+(String)item.get("description");
                            description.setText(descriptiontext);

                            String yearno="Year: "+(String)item.get("year");
                            year.setText(yearno);

                            String starstext="Stars: "+(String)item.get("stars");
                            stars.setText(starstext);

                            String lengthtext="Length: "+(String)item.get("length");
                            length.setText(lengthtext);

                            String directortext="Director: "+(String)item.get("director");
                            director.setText(directortext);

                            String urltext="URL: " +(String)item.get("url");
                            url.setText(urltext);
                        }
                    }
                    button2.setOnClickListener(new RandomClick(position));
                }

            return rootView;
        }
    }

}
