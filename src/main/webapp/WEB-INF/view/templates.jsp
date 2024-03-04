<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        .textClass{
            white-space: nowrap;
            width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .container{
            padding: 10px;
        }
        .noTemplatesContainer{
            text-align: center;
            border-style: solid;
            border-width: 2px;
            border-color: #e1e1e1;
            border-radius: 20px;
            color: #595a59;
            padding: 20px;
        }
        #frownEmoji{
            text-align: center;
            margin: 20px;
            color: #e1e1e1;
        }
    </style>
</head>
<body>
<div class="container">
    <%int i = 0; %>
    <c:if test="${templatesTable.get(0).size() == 0}">
        <div class="noTemplatesContainer">
            <h2>Ooops, you haven't created any templates yet...</h2>
            <div id="frownEmoji"><svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-emoji-frown" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path fill-rule="evenodd" d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
            </svg></div>
            <div class="campaignControlPanel">
                <button type="button" class="btn btn-primary" onclick="location.href='templates/addTemplate'">Create template</button>
            </div>
        </div>
    </c:if>
    <c:if test="${templatesTable.get(0).size() != 0}">
        <c:forEach items="${templatesTable}" var="templateUnit" varStatus="loop">
            <div class="row">
                <c:forEach items="${templateUnit}" var="template">
                    <div class="col-sm">
                        <div class="cardClass">
                            <div class="card text-white bg-dark mb-3">
                                <div class="card-header"><c:out value="${template.title}"/> </div>
                                <div class="card-body">
                                    <h5 class="card-title"><p class="textClassClass"><c:out value="${template.subject}"/></p></h5>
                                    <p class="card-text"><p class="textClass">
                                    <c:out value="${template.body}"/>
                                </p></p>
                                    <a href="#" class="btn btn-primary">Open</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <div class="campaignControlPanel">
            <button type="button" class="btn btn-primary" onclick="location.href='templates/addTemplate'">Create template</button>
        </div>
    </c:if>
</div>
</body>
</html>