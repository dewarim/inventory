<%@ page import="inventory.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="book.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField id="title" name="title" size="60" maxlength="255" required="" value="${bookInstance?.title}"/>
    <script type="text/javascript">
    $('#title').focus()
    </script>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'authorName', 'error')} required">
	<label for="authorName">
		<g:message code="book.authorName.label" default="Author Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authorName" size="60" maxlength="255" required="" value="${bookInstance?.authorName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="book.tags.label" default="Tags" />
		
	</label>
	<g:textArea name="tags" cols="40" rows="5" maxlength="4000" value="${bookInstance?.tags}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="book.comment.label" default="Comment" />
		
	</label>
	<g:textArea name="comment" cols="40" rows="5" maxlength="4000" value="${bookInstance?.comment}"/>
</div>

