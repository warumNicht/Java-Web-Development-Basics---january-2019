
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%--<%response.setContentType("text/html;charset=UTF-8");%>--%>
    <div class="container">
        <main>
            <div class="jumbotron">

                <form action="/tubes/create" method="post">

                    <div class="row">
                        <div class="col col-md-12 d-flex justify-content-center">
                            <h1>Create Tube!</h1>
                        </div>

                    </div>
                    <hr>
                    <div class="row">
                        <div class="col col-md-12">

                            <div class="row d-flex justify-content-center">
                                <label for="nameInput">Name</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input name="name" id="nameInput" type="text">
                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col col-md-12">

                            <div class="row d-flex justify-content-center">
                                <label for="descriptionTextarea">Description</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <textarea style="white-space: pre-line" name="description" id="descriptionTextarea" cols="22" rows="4"></textarea>
                            </div>
                        </div>
                    </div>

                    <hr>
                    <div class="row">
                        <div class="col col-md-12">

                            <div class="row d-flex justify-content-center">
                                <label for="youtubeLink">YouTube Link</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input name="youTubeLink" id="youtubeLink" type="text">
                            </div>
                        </div>
                    </div>

                    <hr>
                    <div class="row">
                        <div class="col col-md-12">
                            <div class="row d-flex justify-content-center">
                                <label for="uploader">Uploader</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input name="uploader" id="uploader" type="text">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col col-md-12 d-flex justify-content-center">
                            <button style="margin-top: 30px" type="submit" class="btn btn-primary">Create tube</button>
                        </div>
                    </div>

                </form>

                <hr>

                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <a href="/">Back to home</a>
                    </div>
                </div>

            </div>
        </main>
        <footer>
            <c:import url="templates/footer.jsp"/>
        </footer>
    </div>
</body>
</html>
