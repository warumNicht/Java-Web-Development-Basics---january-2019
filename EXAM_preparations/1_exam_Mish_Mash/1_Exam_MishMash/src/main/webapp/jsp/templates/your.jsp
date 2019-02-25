<%@ page import="application.domain.models.serviceModels.ChannelServiceModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<h3 class="text-left text-mishmash">Your Channels</h3>
<hr class="bg-mishmash w-100 hr-2"/>

<div class="container-fluid px-lg-0 " >

    <%List<ChannelServiceModel>channels= (List<ChannelServiceModel>) request.getAttribute("your");%>

    <div class="row justify-content-start mx-5 my-5">

        <%
            for (ChannelServiceModel channel : channels) {
               %>

        <div class="jumbotron col-2 text-white bg-mishmash  py-3 px-lg-5 mr-5 ">
            <div class="row text-center">
                <div class="col">
                    <%=channel.getName()%>
                </div>
            </div>
            <hr class="bg-white hr-2"/>
            <div class="row text-center">
                <div class="col">
                    <%=channel.getType()+ " Channel"%>
                </div>
            </div>
            <hr class="bg-white hr-2"/>
            <div class="row text-center">
                <div class="col">
                    <%=channel.getFollowers().size()+" Following"%>
                </div>
            </div>
            <hr class="bg-white hr-2"/>
            <div class="row text-center">
                <div class="col">
                    Following
                </div>
            </div>
        </div>


        <%

            }%>

    </div>
</div>


</html>
