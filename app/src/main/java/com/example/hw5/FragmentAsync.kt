package com.example.hw5

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.hw5.databinding.FrameAsyncBinding
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit


class FragmentAsync : Fragment(), TaskCallbacks {
    private lateinit var binding: FrameAsyncBinding

    private var handler: Handler? = null
    private var callbacks: TaskCallbacks? = null
    private var myTask: MyAsyncTask? = null
    private var adapter: Adapter = Adapter()

    private var listItem: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        callbacks = this
        startTask()
    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg params: Unit?) {
            try {
                for (i in 0..2) {
                    TimeUnit.SECONDS.sleep(1)
                    if (isCancelled) break
                }
            } catch (e: InterruptedIOException) {
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?) {
            callbacks?.let {
                for (i in 1..100) {
                    handler?.sendEmptyMessageDelayed(i, ((i - 1) * 1000).toLong())
                }
            }
        }
    }

    fun getAdapter(): Adapter {
        return adapter
    }

    private fun startTask() {
        myTask = MyAsyncTask()
        val callback = Handler.Callback { msg ->
            callbacks?.onPostExecute("Сообщение №" + msg.what.toString())
            false
        }

        handler = Handler(callback)
        myTask!!.execute()
    }

    override fun onPreExecuted() {
        Log.d("cancel", "executed")
    }

    override fun onCancelled() {
        Log.d("cancel", "canceled")
    }

    override fun onPostExecute(i: String) {
        listItem.add(i)

        val activityCallBack = requireActivity() as FragmentManager
        activityCallBack.setList(listItem)
    }

    companion object {
        const val MyTag = "FRAGMENT_ASYNC"
    }
}