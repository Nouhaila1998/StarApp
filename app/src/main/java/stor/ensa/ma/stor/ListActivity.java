package stor.ensa.ma.stor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import stor.ensa.ma.stor.adapter.StarAdapter;
import stor.ensa.ma.stor.beans.Star;
import stor.ensa.ma.stor.service.StarService;

public class ListActivity extends AppCompatActivity {

    private StarService service;
    private StarAdapter starAdapter = null;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init(){
        service.create(new Star("Monica Belluci","https://photo.astalents.net/media/m/190/190971/monica_bellucci_by_eric_nehr_0_550_550.jpg" , 3.5f));
        service.create(new Star("Jason Statham", "https://resize-elle.ladmedia.fr/r/625,,forcex/crop/625,625,center-middle,forcex,ffffff/img/var/plain_site/storage/images/loisirs/cinema/news/jason-statham-se-verrait-bien-en-james-bond-2956224/54658850-1-fre-FR/Jason-Statham-se-verrait-bien-en-James-Bond.jpg", 3));
        service.create(new Star("Said Bey", "https://aujourdhui.ma/wp-content/uploads/2020/07/Said-Bey.jpg", 5));
        service.create(new Star("Kylie Jenner", "https://i0.wp.com/afriqueshowbiz.com/wp-content/uploads/2021/09/4c81233c57efeca9a6134baf5a67f3de-jpg-5f47b60fdb1b0-1.jpg?w=1500&ssl=1", 1));
        service.create(new Star("Rafik Boubker", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Rafik_Boubker.jpg/440px-Rafik_Boubker.jpg", 5));
        service.create(new Star("Washington", "https://fr.theepochtimes.com/assets/uploads/2020/02/Denzel-Washington-i-700x420.jpg", 1));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        service.findAll().clear();
        Star.setComp(0);
    }
}
