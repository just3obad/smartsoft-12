
    function pie() {
      var chart;
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: 'Browser market shares at a specific website, 2010'
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Firefox',   45.0],
                    ['IE',       26.8],
                    {
                        name: 'Chrome',
                        y: 12.8,
                        sliced: true,
                        selected: true
                    },
                    ['Safari',    8.5],
                    ['Opera',     6.2],
                    ['Others',   0.7]
                ]
            }]
        });
    
    }
    /* This method takes the following parameters 
      dataa is the array of arrays of data it renders
      names is the name given to each array.
      point interval is the x-axis value between two points in the graph
      point start is the start point of the graph in x value
      xl is the label given to x-axis
      yl is the label given to y-axis
      titleOfGraph is the title of the drawn graphs
    */
    function Graph(dataa,names,pointInterval,pointStart,xl,yl,titleOfGraph) {
    var chart;
    var options = {
        chart: {
        renderTo: 'ccc',
        type: 'line',
        marginRight: 130,
        plotBackgroundColor: 'E5E5E5',
        height: 500
    },
    xAxis: {
        type: "datetime",
        title: {
           text: xl,
            style: {
            color: '#999',
            font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
         }
        }
        
      },
      yAxis: {
        title: {
           text: yl,
            style: {
            color: '#999',
            font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
         }
        }
      },
     legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0,
      itemHoverStyle: {
         color: '#000'
      },
      itemHiddenStyle: {
         color: '#333'
      }
            },
      title: {
             text: titleOfGraph,
             style: {
            color: '#999',
            font: 'bold 20px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
         }
            },
    series: [{
        name: names[0],
        data: dataa[0],
        pointInterval: pointInterval,
        pointStart: pointStart
    }]
};
  for(i=1;i<dataa.length;i++) {
    var mli = new Object();
    mli.data = dataa[i];
    mli.name = names[i];
    mli.pointInterval= pointInterval;
    mli.pointStart= pointStart;
    options.series.push(mli);
   }

 chart=new Highcharts.Chart(options);
}
