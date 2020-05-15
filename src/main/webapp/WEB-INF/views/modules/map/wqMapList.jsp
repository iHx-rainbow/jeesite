<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>地图</title>
    <meta name="decorator" content="default" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link href="http://map.zjditu.cn/vmap/static/mapbox-gl.css" rel="stylesheet">
    <style>
        html,
        body {
            width: 100%;
            height: 100%;
            margin: 0;
        }

        .map {
            position: absolute;
            top: 0;
            bottom: 0;
            width: 100%;
        }

        /*自定义控件样式*/
        .menu {
            position: absolute;
            margin-top: 0;
            right: 0;
            width: 300px;
            /*height: 200px;    自定义选择栏高度*/
            background: rgba(255, 255, 255, 0.7);
            z-index: 1;
        }

        .site {
            position: absolute;
            margin-top: 0;
            left: 0;
            width: 200px;
            /*height: 200px;    自定义选择栏高度*/
            background: rgba(255, 255, 255, 0.7);
            padding-bottom: 8px;
            z-index: 1;
        }

        .site-bar {
            background-color: #019FE9;
            color: #ffffff;
            height: 36px;
            line-height: 36px;
            border-radius: 4px 4px 0 0;
            padding: 0 15px 0 35px;
            font-size: 14px;
        }

        .button {
            float: right;
            margin-right: 8px;
        }

        .tool {
            height: 30px;
        }

        .checkboxs {
            margin-top: 10px;
        }

        .checkbox-span {
            float: left;
            margin-left: 8px;
        }

        .tabs {
            width: 300px;
            background-color: #FFF;
            box-sizing: border-box;
            background: rgba(255, 255, 255, 0.7);
        }

        .tabs nav {
            height: 36px;
            text-align: center;
            line-height: 36px;
            overflow: hidden;
            background-color: #019fe9;
            border-radius: 4px 4px 0 0;
            /* 伸缩盒模型 */
            display: flex;
        }

        nav a {
            display: block;
            width: 80px;
            color: #FFF;
            text-decoration: none;
            font-size: 14px;
        }

        nav a:last-child {
            border-right: none;
        }

        nav a.active {
            background-color: deepskyblue;
        }

        .tabs a:hover,
        .tabs a:focus {
            color: aliceblue;
            text-decoration: underline;
        }

        .cont {
            overflow: hidden;
            display: none;
        }

        .cont table {
            margin: auto;
            width: 280px;
        }

        .cont td {
            width: auto;
            padding-left: 10px;
            padding-right: 10px;
            line-height: 30px;
            text-align: center;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>

<body>
    <div>
        <div id="map" class="map">
            <div id="site" class="site">
                <div class="site-bar">
                    站点信息
                </div>
                <div id="checkboxs" class="checkboxs">
                </div>
            </div>

            <div id="menu" class="menu">
                <div class="tool">
                    <button id="target-hz" class="button">定位至杭州</button>
                    <button id="show" class="button">显示</button>
                    <button id="hide" class="button">隐藏</button>
                </div>
                <div id="tabs" class="tabs">
                    <nav id="table-tab">

                    </nav>
                </div>
            </div>
        </div>
    </div>


    <script src="http://map.zjditu.cn/vmap/static/mapbox-gl.js"></script>
    <!--
        引入Microsoft CDN的jQuery.js文件
        最新版本为：https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.js
    -->
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-1.8.0.min.js"></script>



    <script>
        var map = new mapboxgl.Map({
            container: 'map',
            zoom: 12,
            center: [120.15, 30.25],
            renderWorldCopies: false,
            localIdeographFontFamily: "'黑体','san-serif'",
            style: 'http://map.zjditu.cn/vtiles/styles/tdt/streets.json'
        });

        map.addControl(new mapboxgl.NavigationControl({
            showCompass: true,
            showZoom: true
        }), 'bottom-right');

        //var JSONObject = {};  //定义不可修改JSON对象

        map.on('load', function () {
            //初始化JSON
            initJsonData();
            //添加自定义选择栏
            addCheckbox();
        })

        function initJsonData() {
            var object = '${object}';
            console.log(object);
            //console.log(typeof object);
            JSONObject = JSON.parse(object);


            leibie = new Array();
            for (let index in JSONObject.leibie) {
                leibie.push(JSONObject.leibie[index])
            }
            console.log(leibie);

            marker = new Array(leibie.length);
            coordinates = new Array(leibie.length);

        	/*
        	$.ajax({
                type: "get",                //请求方式
                url: "${ctx}/map/wqMap/list",       //地址，就是json文件的请求路径
                dataType: "json",           //数据类型可以为 text xml json script jsonp
                async: false,
                success: function (data) {  //返回的参数就是 action里面所有的有get和set方法的参数
                    JSONObject = data;
                }
                /*
                var dataroot = "location.json";
                $.getJSON(dataroot, function (data) {
                    JSONObject = data;
                */

            /*
            });
            */

            //console.log(JSONObject);
            //JSONObject = $(object);
            //JSONObject = JSON.parse(JSONObject);

        }


        function addCheckbox() {
            delCheckbox();
            var div = document.createElement("div");
            div.setAttribute("id", "checkboxs");
            div.setAttribute("class", "checkboxs");

            for (let index = 0; index < leibie.length; index++) {
                var span = document.createElement("span");
                span.setAttribute("class", "checkbox-span");
                var checkBox = document.createElement("input");
                checkBox.setAttribute("type", "checkbox");
                checkBox.setAttribute("id", index);
                checkBox.setAttribute("value", leibie[index]);
                checkBox.setAttribute("name", "checkbox");
                checkBox.setAttribute("checked", false);
                var text = document.createTextNode(leibie[index]);
                span.appendChild(text);
                span.insertBefore(checkBox, text);

                checkBox.addEventListener("click", clickCheckbox, false);    //true - 事件句柄在捕获阶段执行
                //span.addEventListener("click", clickZoom, false);           //false - 默认，事件句柄在冒泡阶段执行
                div.appendChild(span);
            }

            var element = document.getElementById("site");
            element.appendChild(div);

            let checkboxs = document.getElementsByName("checkbox");
            for (let i = 0; i < checkboxs.length; i++) {
                checkboxs[i].checked = false;
            }
        }

        function delCheckbox() {
            var parent = document.getElementById("site");
            var child = document.getElementById("checkboxs");
            parent.removeChild(child);
        }

        function clickCheckbox(e) {
            //console.log(id);
            //delCheckbox();
            //e.stopPropagation();
            var index = e.target.id;
            if (e.target.checked == true) {
                addMarker(parseInt(index));
                addTable(index);
                //e.target.checked = false;
            } else {
                delMarker(parseInt(index));
                delTable(index);
                //e.target.checked = true;
                /*
                var parent = document.getElementById("table-bar");
                var childs = parent.childNodes;
                for (var i = childs.length - 1; i >= 0; i--) {
                    //alert(childs[i].nodeName);
                    parent.removeChild(childs[i]);
                }
                */
            }
        }

        function addMarker(index) {
            //console.log(typeof index);
            console.log(leibie);
            var lei = leibie[index];
            coordinates[index] = new Array();
            marker[index] = new Array();
            var popup = new Array();

            for (let i = 0; i < JSONObject[lei].length; i++) {
                coordinates[index].push([JSONObject[lei][i].longitude, JSONObject[lei][i].latitude]);

                popup[index] = new mapboxgl.Popup()
                    .setHTML("<h5>" + JSONObject[lei][i].districtname + "</h5>");

                marker[index][i] = new mapboxgl.Marker()
                    .setLngLat(coordinates[index][i])
                    .setPopup(popup[index])
                    .addTo(map);
            }
            /*
            let checkboxs = document.getElementsByName("checkbox");
            for (let i = 0; i < checkboxs.length; i++) {
                checkboxs[i].addEventListener("click", clickCheckbox);
            }
            */
            /*
            const el = document.createElement('div');
            el.className = 'marker';
            el.innerHTML = "<img id='icon' src='http://www.zjweu.edu.cn/_upload/article/images/5c/db/a107da844e8aa34b01d758679a4c/a4cd81de-debf-4c14-a766-e2f821f3efde.jpg' width=40 height=40 />";
            //el.style.backgroundImage = 'url(http://www.zjweu.edu.cn/_upload/article/images/5c/db/a107da844e8aa34b01d758679a4c/a4cd81de-debf-4c14-a766-e2f821f3efde.jpg)';
            el.style.width = '40px';
            el.style.height = '40px';
            el.style.borderRadius = '50%';

            marker3 = new mapboxgl.Marker(el)
                .setLngLat(coordinates[2])
                .setPopup(popup[2]);

            new mapboxgl.Marker()
                .setLngLat(coordinates3)
                .addTo(map);

            marker1.addTo(map);
            marker2.addTo(map);
            marker3.addTo(map);
            */
        }

        function delMarker(index) {
            for (let i = 0; i < marker[index].length; i++) {
                marker[index][i].remove();
            }
        }

        function addTable(index) {
            var tabletab = document.getElementById("table-tab");
            var a = document.createElement("a");
            a.setAttribute("href", "javascript:;");
            a.setAttribute("data-cont", "table" + index);
            a.setAttribute("id", "tab" + index);
            tabletab.appendChild(a);


            var tabs = document.getElementById("tabs");
            var section = document.createElement("section");
            section.setAttribute("class", "cont");
            section.setAttribute("id", "table" + index);

            var table = document.createElement("table");

            var lei = leibie[index];

            table.innerHTML = "<tr><td>序号</td><td>名称</td><td>所属</td></tr>";

            for (let i = 0; i < JSONObject[lei].length; i++) {
                const districtname = JSONObject[lei][i].districtname;
                const name = JSONObject[lei][i].name;

                var tr = document.createElement("tr");
                tr.setAttribute("name", lei + "-" + i);

                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                td1.innerText = i + 1;
                td2.innerText = name;
                td3.innerText = districtname;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                table.appendChild(tr);
                tr.addEventListener("click", function () { clickZoom(lei, i) }, false);
            }

            section.appendChild(table);
            tabs.appendChild(section);

            initTabs();

            var tab = document.getElementById("tab" + index);
            if (tab != null) {
                tab.innerText = leibie[index];
                tab.classList.add("active");
                tab.click();
            }

            /*
            var sec = document.getElementById("table" + index);
            if (sec != null) {
                sec.style.display = "block";
            }
            */

            /*
                div.setAttribute("id", "table-bar");
                div.setAttribute("class", "site-bar");
                var element = document.getElementById("menu");
                element.appendChild(div);
                var parent = document.getElementById("table-bar");
                var childs = parent.childNodes;
                for (var i = childs.length - 1; i >= 0; i--) {
                    //alert(childs[i].nodeName);
                    parent.removeChild(childs[i]);
                }
            }
            tablebar.innerHTML = "<h2>" + leibie[index] + "</h2>";
            //tablebar.innerHTML="<h2>aaaaaa</h2>";
            */
        }

        function delTable(index) {
            var parent = document.getElementById("table-tab");
            var child = document.getElementById("tab" + index);
            var last = document.getElementById("table-tab").lastElementChild;
            //alert(parent.childNodes.length);
            if (last.id == child.id && parent.childNodes.length != 2) {
                var brother = child.previousElementSibling;
                brother.click();
            }
            parent.removeChild(child);

            var parent = document.getElementById("tabs");
            var child = document.getElementById("table" + index);
            parent.removeChild(child);
            /*
            var childs = parent.childNodes;
            for (var i = childs.length - 1; i >= 0; i--) {
                //alert(childs[i].nodeName);
                parent.removeChild(childs[i]);
            }
            */
        }

        //点击缩放
        function clickZoom(lei, i) {
            //e.stopPropagation();
            //alert(e.target.firstChild.id);
            //var name = e.name;
            //var x = new Array();
            //alert(e);
            //alert(lei);
            //alert(i);
            var longitude = parseFloat(JSONObject[lei][i].longitude);
            var latitude = parseFloat(JSONObject[lei][i].latitude);
            map.fitBounds([[longitude - 0.01, latitude - 0.01], [longitude + 0.01, latitude + 0.01]]);
            /*
            x = name.split("-");
            var a=x[0];
            var b=x[1];
            alert(a,b);
            */
            /*
            var longitude = parseFloat(coordinates[index][0]);
            var latitude = parseFloat(coordinates[index][1]);
            //alert(longitude+"+"+latitude);
            map.fitBounds([[longitude - 0.01, latitude - 0.01], [longitude + 0.01, latitude + 0.01]]);
            */
        }

        document.getElementById('target-hz').addEventListener('click', function () {
            map.fitBounds([[120.007561, 30.18352], [120.452178, 30.337722]]);
        })

        document.getElementById('show').addEventListener('click', function () {
            /*
            for (let index = 0; index < JSONObject.amount; index++) {
                marker[index].addTo(map);
            }
            */
            let checkboxs = document.getElementsByName("checkbox");
            for (let i = 0; i < checkboxs.length; i++) {
                if (checkboxs[i].checked == false) {
                    checkboxs[i].checked = true;
                    let index = checkboxs[i].id;
                    addMarker(parseInt(index));
                }
            }
        })

        document.getElementById('hide').addEventListener('click', function () {
            /*
            for (let index = 0; index < JSONObject.amount; index++) {
                marker[index].remove();
            }
            */
            let checkboxs = document.getElementsByName("checkbox");
            for (let i = 0; i < checkboxs.length; i++) {
                if (checkboxs[i].checked == true) {
                    checkboxs[i].checked = false;
                    let index = checkboxs[i].id;
                    delMarker(parseInt(index));
                }
            }
        })

    </script>

    <script>
        function initTabs() {
            //document.querySelectorAll获取所有的a标签
            var aArr = document.querySelectorAll('a');
            //循环遍历
            for (var i = 0; i < aArr.length; i++) {
                //给每个获取到的元素添加点击事件
                aArr[i].onclick = function () {
                    //获取当前激活的tab选项卡
                    var active = document.querySelector(".active");
                    //移除之前的选项卡激活属性
                    active.classList.remove("active");
                    //给当前点击的选项卡添加激活属性
                    this.classList.add("active");
                    //获取当前的section标签id名字
                    var name = this.getAttribute("data-cont");
                    //根据获取到的名字获取当前的内容卡
                    var section = document.getElementById(name);
                    //获取所有class为cont的元素，并循环遍历，取消所有内容卡的样式
                    var cont = document.getElementsByClassName("cont");
                    for (var i = 0; i < cont.length; i++) {
                        cont[i].style.display = "none";

                    }
                    //激活当前内容卡的样式
                    section.style.display = "block";
                }
            }
        }

    </script>


</body>

</html>