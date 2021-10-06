package app.christopher.beautyroomapp.ui.dashboard

import android.app.ProgressDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment: Fragment() {
    private lateinit var dialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
       // val toolbar = view.findViewById<MaterialToolbar>(R.id.dashboard_toolbar)
       // (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(requireContext())

        cv_spec1.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_servicesActivity)
        }
        cv_spec2.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_servicesActivity)
        }
        cv_lashes.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_servicesActivity)
        }
        getInTouch.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_servicesActivity)
        }
        /*
        val items : ArrayList<Specialists> = ArrayList()

        //List of images for horizontal scroll view.
        items.add(Specialists(R.drawable.spec1, "Facial Treatment", "07003428612"))
        items.add(Specialists(R.drawable.spec2, "Makeup", "08883428609"))
        items.add(Specialists(R.drawable.spec3, "Makeup Session", "08883428609"))
        items.add(Specialists(R.drawable.spec4, "Personal Facials", "07883758612"))
        items.add(Specialists(R.drawable.spec5, "Epic Nails", "08003428612"))
        items.add(Specialists(R.drawable.spec6, "Makeup Session", "08883428609"))
        items.add(Specialists(R.drawable.spec7, "Skin Care", "08980028612"))

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = SpecialistsAdapter(items)
        recycler_view.setHasFixedSize(true)
        */
    }

}
        /*
        var list = mutableListOf<Int>()

        list.add(R.drawable.black_beauty)
        list.add(R.drawable.blackfriday_deals)
        list.add(R.drawable.black_beauty)
        list.add(R.drawable.screen1)
        list.add(R.drawable.facial_care)
        list.add(R.drawable.hair_rebonding)
        list.add(R.drawable.screen2)
        list.add(R.drawable.massage)
        list.add(R.drawable.mohawk_braids)
        list.add(R.drawable.screen3)

        sliderAdapter = HomeSliderAdapter(context)
        sliderAdapter.setContentList(list)
        slider_viewPager.adapter = sliderAdapter
        */