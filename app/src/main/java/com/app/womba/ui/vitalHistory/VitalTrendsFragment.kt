package com.app.womba.ui.vitalHistory

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.R
import com.app.womba.model.TrendData
import com.app.womba.model.VitalsTrendResponse
import com.app.womba.model.postResponseModel.VitalPost
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import com.evitalapp.model.*
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.vital_trends_fragment.*
import java.util.*

class VitalTrendsFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    companion object {
        fun newInstance() = VitalTrendsFragment()
    }

    private lateinit var viewModel: VitalTrendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vital_trends_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VitalTrendsViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        viewModel.getResponse().observe(this,
            Observer<VitalsTrendResponse> { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    initData(mData.data)
                }
            })

        radioGroup.setOnCheckedChangeListener(this)
        callNetworkForVitalTrendHistory(getPreviousDate(7), getCurrentDate())
        initGraphs()
    }

    private fun initGraphs() {
        initHRGraph()
        initHRVGraph()
        initStressGraph()
        initRepirationGraph()
        initOxygenGraph()
    }

    private fun initOxygenGraph() {
        oxygenBarGraph.description.isEnabled = false
        oxygenBarGraph.setPinchZoom(false)
        oxygenBarGraph.setDrawBarShadow(false)
        oxygenBarGraph.setDrawGridBackground(false)

        val xAxis: XAxis = oxygenBarGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = oxygenBarGraph.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setCenterAxisLabels(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        oxygenBarGraph.axisRight.isEnabled = false
        oxygenBarGraph.legend.isEnabled = false
    }

    private fun initRepirationGraph() {
        respirationBarGraph.description.isEnabled = false
        respirationBarGraph.setPinchZoom(false)
        respirationBarGraph.setDrawBarShadow(false)
        respirationBarGraph.setDrawGridBackground(false)

        val xAxis: XAxis = respirationBarGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = respirationBarGraph.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setCenterAxisLabels(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        respirationBarGraph.axisRight.isEnabled = false
        respirationBarGraph.legend.isEnabled = false
    }

    private fun initStressGraph() {

        stressBarGraph.description.isEnabled = false
        // scaling can now only be done on x- and y-axis separately
        stressBarGraph.setPinchZoom(false)
        stressBarGraph.setDrawBarShadow(false)
        stressBarGraph.setDrawGridBackground(false)

        val xAxis: XAxis = stressBarGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = stressBarGraph.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setCenterAxisLabels(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        stressBarGraph.axisRight.isEnabled = false
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        stressBarGraph.legend.isEnabled = false
    }

    private fun initHRGraph() {
        heartBarGraph.description.isEnabled = false
        // scaling can now only be done on x- and y-axis separately
        heartBarGraph.setPinchZoom(false)
        heartBarGraph.setDrawBarShadow(false)
        heartBarGraph.setDrawGridBackground(false)
        heartBarGraph.setMaxVisibleValueCount(5)


        val xAxis: XAxis = heartBarGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = heartBarGraph.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setCenterAxisLabels(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        heartBarGraph.axisRight.isEnabled = false
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        heartBarGraph.legend.isEnabled = false
    }

    private fun initHRVGraph() {
        hrvBarGraph.description.isEnabled = false
        // scaling can now only be done on x- and y-axis separately
        hrvBarGraph.setPinchZoom(false)
        hrvBarGraph.setDrawBarShadow(false)
        hrvBarGraph.setDrawGridBackground(false)
        hrvBarGraph.setMaxVisibleValueCount(5)


        val xAxis: XAxis = hrvBarGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = hrvBarGraph.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setCenterAxisLabels(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        hrvBarGraph.axisRight.isEnabled = false
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        hrvBarGraph.legend.isEnabled = false
    }

    private fun callNetworkForVitalTrendHistory(
        fromDate: String,
        toDate: String
    ) {
        date.post {
            date.text = "${changeDateFormat(
                "MM/dd/yyyy",
                "MMMM dd,yyyy",
                fromDate
            )} - ${changeDateFormat("MM/dd/yyyy", "MMMM dd,yyyy", toDate)}"
        }
        viewModel.getTrendHistory(
            VitalPost(
                getUserData().data!!.get_id()!!,
                fromDate, toDate
            )
        )
    }

    fun initData(data: TrendData?) {
        data?.let {
            it.hrScanList?.let { list ->
                if (list.isNotEmpty()) {
                    setHRBarGraph(list)
                }
            }
            it.respirationScanList?.let { list ->
                if (list.isNotEmpty()) {
                    setRespirationBarGraph(list)
                }
            }
            it.stressScanList?.let { list ->
                if (list.isNotEmpty()) {
                    setStressBarGraph(list)
                }
            }
            it.oxygenScanList?.let { list ->
                if (list.isNotEmpty()) {
                    setOxygenBarGraph(list)
                }
            }
            it.hrvScanList?.let { list ->
                if (list.isNotEmpty()) {
                    setHRVBarGraph(list)
                }
            }
        }
    }

    fun setHRVBarGraph(list: List<HrvScan>) {
        val (values1, date) = getHRVBarValues(list)
        setDates(hrvBarGraph, date)
        val set1: BarDataSet
        if (hrvBarGraph.data != null && hrvBarGraph.data.dataSetCount > 0) {
            set1 = hrvBarGraph.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            hrvBarGraph.data.notifyDataChanged()
            hrvBarGraph.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "")
            set1.color = Color.parseColor("#A0E5FF")
            val data = BarData(set1)
            hrvBarGraph.data = data
        }
        hrvBarGraph.barData.barWidth = 0.3f
        hrvBarGraph.setVisibleXRangeMaximum(5f)
        hrvBarGraph.setFitBars(false)
        hrvBarGraph.invalidate()
    }

    fun setOxygenBarGraph(list: List<OxygenScan>) {
        val (values1, date) = getOxygenBarValues(list)
        setDates(oxygenBarGraph, date)
        val set1: BarDataSet
        if (oxygenBarGraph.data != null && oxygenBarGraph.data.dataSetCount > 0) {
            set1 = oxygenBarGraph.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            oxygenBarGraph.data.notifyDataChanged()
            oxygenBarGraph.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "")
            set1.color = Color.parseColor("#A0E5FF")
            val data = BarData(set1)
            oxygenBarGraph.data = data
        }
        oxygenBarGraph.barData.barWidth = 0.3f
        oxygenBarGraph.setVisibleXRangeMaximum(5f)
        oxygenBarGraph.setFitBars(false)
        oxygenBarGraph.invalidate()
    }

    fun setStressBarGraph(list: List<StressScan>) {
        val (values1, date) = getStressBarValues(list)
        setDates(stressBarGraph, date)
        val set1: BarDataSet
        if (stressBarGraph.data != null && stressBarGraph.data.dataSetCount > 0) {
            set1 = stressBarGraph.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            stressBarGraph.data.notifyDataChanged()
            stressBarGraph.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "")
            set1.color = Color.parseColor("#A0E5FF")
            val data = BarData(set1)
            stressBarGraph.data = data
        }
        stressBarGraph.barData.barWidth = 0.3f
        stressBarGraph.setVisibleXRangeMaximum(5f)
        stressBarGraph.setFitBars(false)
        stressBarGraph.invalidate()
    }

    fun setRespirationBarGraph(list: List<RespirationScan>) {
        val (values1, date) = getRespirationBarValues(list)
        setDates(respirationBarGraph, date)
        val set1: BarDataSet
        if (respirationBarGraph.data != null && respirationBarGraph.data.dataSetCount > 0) {
            set1 = respirationBarGraph.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            respirationBarGraph.data.notifyDataChanged()
            respirationBarGraph.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "")
            set1.color = Color.parseColor("#A0E5FF")
            val data = BarData(set1)
            respirationBarGraph.data = data
        }
        respirationBarGraph.barData.barWidth = 0.3f
        respirationBarGraph.setVisibleXRangeMaximum(5f)
        respirationBarGraph.setFitBars(false)
        respirationBarGraph.invalidate()
    }

    fun setHRBarGraph(list: List<HrScan>) {
        val (values1, date) = getHRBarValues(list)
        setDates(heartBarGraph, date)
        val set1: BarDataSet
        if (heartBarGraph.data != null && heartBarGraph.data.dataSetCount > 0) {
            set1 = heartBarGraph.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            heartBarGraph.data.notifyDataChanged()
            heartBarGraph.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "")
            set1.color = Color.parseColor("#A0E5FF")
            val data = BarData(set1)
            heartBarGraph.data = data
        }

        heartBarGraph.barData.barWidth = 0.3f
        heartBarGraph.setVisibleXRangeMaximum(5f)
        heartBarGraph.setFitBars(false)
        heartBarGraph.invalidate()
    }

    fun setDates(
        chart: BarChart,
        date: ArrayList<String>
    ) {
        chart.xAxis.valueFormatter = IndexAxisValueFormatter(date.toList())
    }


    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p1) {
            R.id.lastWeek -> {
                callNetworkForVitalTrendHistory(getPreviousDate(7), getCurrentDate())
            }
            R.id.last14Days -> {
                callNetworkForVitalTrendHistory(getPreviousDate(14), getCurrentDate())
            }
            R.id.last21Days -> {
                callNetworkForVitalTrendHistory(getPreviousDate(21), getCurrentDate())
            }

        }
    }


    fun getHRBarValues(list: List<HrScan>): HR {
        val values1 = ArrayList<BarEntry>()
        val date = ArrayList<String>()

        for (i in list.indices) {
            list[i].reportdate?.let {
                date.add(changeDateFormat("dd-MM-yyyy", "MM/dd", it))
            }
            list[i].hr?.getMeanPulseRate()?.getValue()?.let {
                values1.add(BarEntry(i.toFloat(), it.toFloat()))
            }
        }
        return HR(values1, date)
    }

    fun getRespirationBarValues(list: List<RespirationScan>): HR {
        val values1 = ArrayList<BarEntry>()
        val date = ArrayList<String>()
        for (i in list.indices) {
            list[i].reportdate?.let {
                date.add(changeDateFormat("dd-MM-yyyy", "MM/dd", it))
            }
            list[i].respiration?.getValue()?.let {
                values1.add(BarEntry(i.toFloat(), it.toFloat()))
            }
        }
        return HR(values1, date)
    }

    fun getStressBarValues(list: List<StressScan>): HR {
        val values1 = ArrayList<BarEntry>()
        val date = ArrayList<String>()
        for (i in list.indices) {
            list[i].reportdate?.let {
                date.add(changeDateFormat("dd-MM-yyyy", "MM/dd", it))
            }
            list[i].stress?.getValue()?.let {
                values1.add(BarEntry(i.toFloat(), it.toFloat()))
            }
        }
        return HR(values1, date)
    }

    fun getOxygenBarValues(list: List<OxygenScan>): HR {
        val values1 = ArrayList<BarEntry>()
        val date = ArrayList<String>()
        for (i in list.indices) {
            list[i].reportdate?.let {
                date.add(changeDateFormat("dd-MM-yyyy", "MM/dd", it))
            }
            list[i].oxygen?.getSaturationLevel()?.getValue()?.let {
                values1.add(BarEntry(i.toFloat(), it.toFloat()))
            }
        }
        return HR(values1, date)
    }

    fun getHRVBarValues(list: List<HrvScan>): HR {
        val values1 = ArrayList<BarEntry>()
        val date = ArrayList<String>()
        for (i in list.indices) {
            list[i].reportdate?.let {
                date.add(changeDateFormat("dd-MM-yyyy", "MM/dd", it))
            }
            list[i].sdrr?.getValue()?.let {
                values1.add(BarEntry(i.toFloat(), it.toFloat()))
            }
        }
        return HR(values1, date)
    }

    data class HR(val values: ArrayList<BarEntry>, val dates: ArrayList<String>)
}


