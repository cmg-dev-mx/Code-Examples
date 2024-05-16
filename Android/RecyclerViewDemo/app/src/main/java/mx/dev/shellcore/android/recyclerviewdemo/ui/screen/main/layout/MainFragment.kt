package mx.dev.shellcore.android.recyclerviewdemo.ui.screen.main.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemAlert
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemCommon
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemPermission
import mx.dev.shellcore.android.recyclerviewdemo.databinding.FragmentMainBinding
import mx.dev.shellcore.android.recyclerviewdemo.ui.adapter.ItemAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val items = listOf(
        ItemCommon(
            "Option 1",
            "This is the option 1",
        ),
        ItemCommon(
            "Option 2",
            "This is the option 2",
        ),
        ItemAlert(
            title = "Alert! You need to do something here."
        ),
        ItemPermission(
            title = "Go to ask permission"
        )
    )

    private val itemAdapter = ItemAdapter(items) { item ->
        if (item is ItemPermission) {
            Snackbar.make(
                binding.mainContainer,
                "Item to ask permission pressed",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.mainItemsRec.apply {
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = itemAdapter
            setHasFixedSize(true)
        }

        return binding.root
    }
}