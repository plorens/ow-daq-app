google.charts.load('current', {'packages':['annotatedtimeline']});
//google.charts.setOnLoadCallback(drawChart);


 var date = new Date();
    year = date.getFullYear();
    prev_month = addZero(date.getMonth());
    month = addZero(date.getMonth()+1);
    prev_day = addZero(date.getDate()-1);
    day = addZero(date.getDate());
    hour = addZero(date.getHours());
    minute = addZero(date.getMinutes());


function drawPrevDayChart(){

prev_day = prev_day - 1;
day = day -1;
/*
if(prev_day==0){
    if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12){
        prev_day= 30;
        prev_month=month-1;
        continue;
    }
}*/



    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;
    drawChart(begin, end);
}

function drawNextDayChart(){


/*
if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12)&&(begin_day==31))
{
    prev_day = 1;
    day = 2;
}
else if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12)&&(day==1))
{
    prev_day = 29;
    day= 30;
}
if((month==4)||(month==6)||(month==9)||(month==11)&&(prev_day==30))
{
    prev_day = 1;
    day = 2;
}
else if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12)&&(day==1))
{
    prev_day = 29;
    day= 30;
}*/


    prev_day = prev_day + 1;
    day = day + 1;

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;
    drawChart(begin, end);
}


function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }

    return i;
}


function drawTodayDateChart(){

    var date = new Date();
    year = date.getFullYear();
    month = addZero(date.getMonth()+1);
    prev_day = addZero(date.getDate());
    day = addZero(date.getDate())+1;
    hour = 00;
    minute = 00;

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;
    drawChart(begin, end);

}

function draw24hFromNow(){

    var date = new Date();
    year = date.getFullYear();
    month = addZero(date.getMonth()+1);
    prev_day = addZero(date.getDate()-1);
    day = addZero(date.getDate());
    hour = addZero(date.getHours());
    minute = addZero(date.getMinutes());

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;
    drawChart(begin, end);

}



function drawChartFromTimeSelect(){

    var begin_year = document.getElementById("begin_year").value;
    var begin_month = document.getElementById("begin_month").value;
    var begin_day = document.getElementById("begin_day").value;
    var begin_time = document.getElementById("begin_time").value;

    var end_year = document.getElementById("end_year").value;
    var end_month = document.getElementById("end_month").value;
    var end_day = document.getElementById("end_day").value;
    var end_time = document.getElementById("end_time").value;

    var begin= begin_year+'-'+begin_month+'-'+begin_day+' '+begin_time;
    var end= end_year+'-'+end_month+'-'+end_day+' '+end_time;

    drawChart(begin, end);
}

function drawChart(begin, end) {

    var siteList= getSiteListFromREST();

    var data = [];
    var columns = [];

    for(var j = 0; j < siteList.length; j++)
    {
        data[j] = new google.visualization.DataTable();
        data[j].addColumn('date', 'Date');
        data[j].addColumn('number', siteList[j].description);
        data[j].addColumn('string', "'title'+i");
        data[j].addColumn('string', "'text'+i");

        var avgReadings = getAvgReadingsFromREST(siteList[j].id, begin, end);
        for(var i = 0; i < avgReadings.length; i++)
        {
            data[j].addRow( [new Date(avgReadings[i].timestamp), avgReadings[i].value, undefined, undefined ] );
        }

        if (j != 0)
        {
            columns.push(j);
            joinedData = google.visualization.data.join(joinedData, data[j], 'full', [[0, 0]], columns, [1]);
        }else{
            var joinedData = data[j];
        }
    }


    var options = {
        thickness: 2,
        fill: 13,
        scaleType: "allmaximized",
        scaleFormat: '#.#',
        displayExactValues: true,
        displayAnnotations: false,
        displayAnnotationsFilter: false,
        displayDateBarSeparator: true,
        dateFormat: "yyyy-MM-dd HH:mm",
        legendPosition: "newRow",
        displayRangeSelector: true,
        interpolateNulls: true
    };

    var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div'));
    chart.draw(joinedData, options);
}




function getAvgReadingsFromREST(siteID, begin, end) {
    var readings = [{"site_ID":13,"timestamp":"2019-09-12T00:02:00.000+0200","value":22.5}];

    $.ajax({
        async: false,
        url: "/API/avgReadingsByDate?siteid="+siteID+"&begin="+begin+"&end="+end,
        type:'get',
        success: function(result)
            {
                readings=result;
            },
        error: function()
            {
                alert("Error - Ajax Call !");
            }
        });
    return readings;
}



function getSiteListFromREST() {
    var siteList = [{"id":42,"description":"okno obok Marka","status":"A"}];

    $.ajax({
        async: false,
        url: "/API/siteList",
        type:'get',
        success: function(result)
            {
                siteList=result;
            },
        error: function()
            {
                alert("Error - Ajax Call !");
            }
        });
    return siteList;
}
