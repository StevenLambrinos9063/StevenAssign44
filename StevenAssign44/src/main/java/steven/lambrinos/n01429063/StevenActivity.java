//Steven Lambrinos N01429063 RNA
package steven.lambrinos.n01429063;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class StevenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment steven_home;
    private Fragment lambrinos_download;
    private Fragment n01429063_weather;
    private Fragment steven_file;
    private Fragment settings_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    configureToolBar();
    configureDrawerLayout();
    configureNavigationView();

        if (this.steven_home == null) this.steven_home = StevenHome.newInstance();
    startTransactionFragment(this.steven_home);
}
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.BackTitle)
                    .setIcon(R.drawable.caution)
                    .setMessage(R.string.BackMessage)
                    .setCancelable(false)
                    .setPositiveButton(R.string.BackPositive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.BackNegative, null)
                    .show();
        }
    }
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.steven_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.steven_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close){
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Snackbar snackbar = Snackbar.make(drawerView, R.string.snackbar, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.steven_main_toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                if (this.steven_home == null) this.steven_home = StevenHome.newInstance();
                startTransactionFragment(this.steven_home);
                break;
            case R.id.download:
                if (this.lambrinos_download == null)
                    this.lambrinos_download = LambrinosDownload.newInstance();
                startTransactionFragment(this.lambrinos_download);
                break;
//            case R.id.weather:
//                if (this.n01429063_weather == null)
//                    this.n01429063_weather = N01429063Weather.newInstance();
//                startTransactionFragment(this.n01429063_weather);
//                break;
            case R.id.steven_fileContent:
                if (this.steven_file == null) this.steven_file = StevenFileContent.newInstance();
                startTransactionFragment(this.steven_file);
                break;
            case R.id.settings:
                if (this.settings_screen == null)
                    this.settings_screen = SettingsScreen.newInstance();
                startTransactionFragment(this.settings_screen);
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steven_main_frame_layout, fragment).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stevenHelp:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.Link)));
                startActivity(i);
                break;
            case R.id.stevenSettings:
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                break;
            case R.id.stevenHome:
                if (this.steven_home == null) this.steven_home = StevenHome.newInstance();
                startTransactionFragment(this.steven_home);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }}


