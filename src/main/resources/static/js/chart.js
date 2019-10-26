google.charts.load('current', {'packages':['annotatedtimeline']});
//google.charts.setOnLoadCallback(drawChart);


function drawChartBetweenDates() {
    var x = document.getElementById("start").value;
    var y = document.getElementById("end").value;

    document.getElementById("showStart").innerHTML = x.replace("T", " ");
    document.getElementById("showEnd").innerHTML = y.replace("T", " ");

    if( x < y )
    {
        var begin= x.replace("T", " ");
        var end= y.replace("T", " ");
        drawChart(begin, end);
    }
    else{
    alert("Wprowadź poprawne daty")}
}


function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }

    return i;
}


var date = new Date();
    year = date.getFullYear();
    month = addZero(date.getMonth()+1);
    prev_day = addZero(date.getDate()-1);
    day = addZero(date.getDate());
    hour = addZero(date.getHours());
    minute = addZero(date.getMinutes());

function drawPrevDayChart(){

    prev_day = prev_day - 1;
    day = day -1;

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;

    document.getElementById("showStart").innerHTML = begin;
    document.getElementById("showEnd").innerHTML = end;
    drawChart(begin, end);
}

function drawNextDayChart(){

    prev_day = prev_day + 1;
    day = day + 1;

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;

    document.getElementById("showStart").innerHTML = begin;
    document.getElementById("showEnd").innerHTML = end;
    drawChart(begin, end);
}


function drawTodayDateChart(){

    var date = new Date();
    year = date.getFullYear();
    month = addZero(date.getMonth()+1);
    prev_day = addZero(date.getDate());
    day = addZero(date.getDate())+1;
    hour = addZero(0);
    minute = addZero(0);

    var begin= year+'-'+month+'-'+prev_day+' '+hour+':'+minute;
    var end= year+'-'+month+'-'+day+' '+hour+':'+minute;

    document.getElementById("showStart").innerHTML = begin;
    document.getElementById("showEnd").innerHTML = end;
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

    document.getElementById("showStart").innerHTML = begin;
    document.getElementById("showEnd").innerHTML = end;
    drawChart(begin, end);
}






















// YYYY-MM-DD hh:mm //
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
