<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript library="jquery" plugin="jquery" />
    <title>What Are You Doing?</title>
</head>
<body>
	
	<h1>Search For People To Follow</h1>
    <div class="searchForm">
        <g:form controller="searchable" name="userSearchForm">
            <g:textField name="q" value=""/>
            <g:submitButton name="Search For Users" id="search_for_users_button"/>
        </g:form>
    </div>
    
    <h1>What Are You Doing?</h1>
    <div class="updatStatusForm">
        <g:formRemote
        	url="[action: 'updateStatus']"
        	update="twitList"
        	name="updateStatusForm"
        	onSuccess="document.getElementById('message').value='';">
            <g:textArea name="message" value=""/><br/>
            <g:submitButton name="Update Status" id="update_status_button"/>
        </g:formRemote>
    </div>
    <div id="twitList">
    	<g:render template="messages" collection="${messages}" var="message"></g:render>
    </div>
</body>
</html>