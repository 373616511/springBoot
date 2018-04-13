<!DOCTYPE html>

<html lang="en">
<head>
    <script type="text/javascript" src="/jquery-2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        $(function () {
            //alert("ok");
        });
    </script>
</head>
<body>
Date: ${time?date}
<br>
Time: ${time?time}

<br>
<h5>用户名:${user.name}</h5>
通过Mapper查询的数据：${message2}

<form action="/submit">
    <textarea cols="80" class="ckeditor" id="editor1" name="editor1" rows="10">
    ${user.text}
    </textarea>
    <input type="submit" value="提交">
</form>

</body>

</html>