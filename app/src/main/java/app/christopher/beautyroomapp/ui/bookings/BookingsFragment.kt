package app.christopher.beautyroomapp.ui.bookings

import androidx.fragment.app.Fragment
import app.christopher.beautyroomapp.R

class BookingsFragment : Fragment(R.layout.fragment_bookings) {
/*
    private val TAG = "BookingsFragment"
    private lateinit var firebaseAuth: FirebaseAuth
    private var adapter: FirestoreRecyclerAdapter<PaymentsModel, FirestorePaymentAdapter.PaymentModelHolder>? = null
    private var firestoreListener: ListenerRegistration? = null
    private var firebaseFirestore: FirebaseFirestore? = null
    private var bookingsList = mutableListOf<PaymentsModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth

        firebaseFirestore = FirebaseFirestore.getInstance()
        loadBookingsList()

        firestoreListener = firebaseFirestore!!.collection(firebaseAuth?.currentUser.email)
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e(TAG, "Listen Failed", e)
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    return@EventListener
                }
                bookingsList = mutableListOf<PaymentsModel>()

                for (doc in documentSnapshots!!) {
                    val note = doc.toObject(PaymentsModel::class.java)
                    bookingsList.add(note)
                }
                adapter!!.notifyDataSetChanged()
                bookings_rv.adapter = adapter
            })

        val simpleCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    //delete from Firestore DB
                    deleteBooking()
                    //Delete from RecyclerView list
                    val position = viewHolder.adapterPosition
                    bookingsList.removeAt(position)
                    adapter!!.notifyItemRemoved(position)
                    adapter!!.notifyDataSetChanged()
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(bookings_rv)
    }

    private fun loadBookingsList() {
        val query = firebaseFirestore!!.collection(firebaseAuth.currentUser.email)
        val response = FirestoreRecyclerOptions.Builder<PaymentsModel>()
            .setQuery(query, PaymentsModel::class.java)
            .build()
        adapter = FirestorePaymentAdapter(response)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        bookings_rv?.layoutManager = linearLayoutManager
        bookings_rv?.itemAnimator = DefaultItemAnimator()


        /*
        adapter = object : FirestorePaymentAdapter(response){
            override fun onBindViewHolder(holder: PaymentModelHolder, position: Int, model: PaymentsModel ) {
                super.onBindViewHolder(holder, position, model)
                val bookings = bookingsList[position]
               deleteBooking()
            }
        }
        */
    }
    private fun deleteBooking() {
        firebaseFirestore!!.collection(firebaseAuth.currentUser.email)
            .document()
            .delete()
            .addOnCompleteListener { task ->
                deleteDialog()
            }
    }
    private fun deleteDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage("Booking deleted")
        dialog.setCancelable(true)
        dialog.setPositiveButton("OKAY"){dialogInt, which ->
            dialogInt.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        firestoreListener!!.remove()
    }

    public override fun onStart() {
        super.onStart()

        adapter!!.startListening()
    }

    public override fun onStop() {
        super.onStop()

        adapter!!.stopListening()
    }
    */
}