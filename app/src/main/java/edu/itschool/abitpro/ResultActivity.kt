package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.itschool.abitpro.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private var binding: ActivityResultBinding? = null

    private var adapter: SearchAdapter? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(getLayoutInflater())
        recyclerView = binding?.recyclerView
        recyclerView?.setAdapter(adapter)

        adapter = SearchAdapter { clickedItem ->
            val intent = Intent(
                this,
                HeiResultActivity::class.java
            ).apply {}
            intent.putExtra("ClickedItemId", clickedItem.id)


        }

        startActivity(intent)

    }
}