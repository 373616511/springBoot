<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src="/jquery-2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        $(function () {
            //alert("ok");
            $("#browseImage").click(function () {
                //方式1：
                // var finder = new CKFinder();
                //var finder = new CKFinder();
                //finder.basePath = '/ckfinder/?type=images'; //导入CKFinder的路径
                //  finder.selectActionFunction = setFileField; //设置文件被选中时的函数
                // finder.popup();

                //方式2：
                var url = "/ckfinder/ckfinder.html?type=Images&action=js&func=setFileField";
                window.open(url);
            });

            $("#file").click(function () {
                window.open("/ckfinder/ckfinder.html");
            });
        });

        function setFileField(fileUrl, data) {
            // alert("ok");
            alert(fileUrl)
            $("#inputId").attr("src", fileUrl);
        }

    </script>
</head>
<body>
Date: ${time?date}
<br>
Time: ${time?time}

<br>
<h5>用户名:${user.name}</h5>
通过Mapper查询的数据：${message2}

<h5>
    <button id="file">文件管理</button>
</h5>
<img style="width: 200px;height: 200px;" src="" id="inputId">
<button id="browseImage">浏览</button>
<form action="/submit">
    <textarea cols="80" class="ckeditor" id="editor1" name="editor1" rows="10">
    ${user.text}
    </textarea>
    <input type="submit" value="提交">
</form>

</body>

</html>