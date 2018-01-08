package me.aungkooo.fabmenutest;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.aungkooo.fabmenu.FabMenu;
import me.aungkooo.fabmenu.OnFabMenuItemClickListener;

public class MainActivity extends AppCompatActivity implements OnFabMenuItemClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabMain = findViewById(R.id.fab_main);

        FloatingActionButton[] fabList = new FloatingActionButton[]{
                findViewById(R.id.fab_message),
                findViewById(R.id.fab_call),
                findViewById(R.id.fab_video)
        };

        FabMenu fabMenu = findViewById(R.id.fab_menu);
        fabMenu.setFabList(fabList).setFabMain(fabMain).setFabMenuItemClickListener(this);
    }

    @Override
    public void onFabMenuItemClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.fab_message:
                Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_call:
                Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_video:
                Toast.makeText(this, "Video call", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
