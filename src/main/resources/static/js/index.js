

var currentReadings= getCurrReadingsFromREST();

tableSiteNameCreate();
google.charts.load('current', {'packages':['gauge']});
google.charts.setOnLoadCallback(drawChart);
tableTimestampCreate();



    function tableSiteNameCreate() {

        var len = currentReadings.length;

        var body = document.getElementsByTagName('div')[0];
        var tbl = document.createElement('table');
        var tbdy = document.createElement('tbody');

        var tr = document.createElement('tr');
        for (var j = 0; j < len; j++)
        {
            var td = document.createElement('td');
            td.appendChild(document.createTextNode(currentReadings[j].siteName));
            td.style.width = '200px';
            td.style.minWidth = '200px';
            td.style.textAlign = 'center';
            tr.appendChild(td)
        }
        tbdy.appendChild(tr);
        tbl.appendChild(tbdy);
        body.appendChild(tbl)
    }



    function tableTimestampCreate() {

        var len = currentReadings.length;

        var body = document.getElementsByTagName('div')[2];
        var tbl = document.createElement('table');
        var tbdy = document.createElement('tbody');

        var tr = document.createElement('tr');

        for (var j = 0; j < len; j++)
        {
            var td = document.createElement('td');
            var d = new Date(currentReadings[j].timestamp);
            td.appendChild(document.createTextNode(d.toLocaleString()));
            td.style.width = '200px';
            td.style.minWidth = '200px';
            td.style.textAlign = 'center';
            td.style.whiteSpace = "nowrap";
            tr.appendChild(td)
        }
        tbdy.appendChild(tr);
        tbl.appendChild(tbdy);
        body.appendChild(tbl)
    }



    function getCurrReadingsFromREST() {
        var currReadings = [];

        $.ajax(
            {
                async: false,
                url: "/API/currentReadings",
                type:'get',
                success: function(result)
                        {
                            currReadings=result;
                        },
                error: function()
                        {
                            alert("Error - Ajax Call !");
                        }
            }
        );
        return currReadings;
    }


    function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Sensor');
        data.addColumn('number', 'Reading');

        var jason = getCurrReadingsFromREST();
        for(var i = 0; i < jason.length; i++)
        {
            var obj = jason[ i ];
            data.addRow([
                '°C',
                obj.temperature
                ]);
        }

        var widthTable= 200*jason.length;
        var options = {
            width: widthTable, height: 200,
            redFrom: 30, redTo: 40,
            yellowFrom:24, yellowTo: 30,
            greenFrom:20, greenTo: 24,
            minorTicks: 10,
            max: 40,
            min: 10
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
        chart.draw(data, options);

        setInterval(   function() {
            var currentReadings= getCurrReadingsFromREST();
            var len = currentReadings.length;

            for ( var i=0; i < len;  i++)
            {
                data.setValue(i, 1, currentReadings[i].temperature);
            }
            chart.draw(data, options);
        }, 1000);
    }
