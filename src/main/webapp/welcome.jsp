<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
  <link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.1/jquery.rateyo.min.css">

  <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <title>EnforcementTemplateAdd</title>
<script type="text/javascript" src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=qagffr3pkuv17a8on1afax661irst1hbr4e6tbv888sz91jc"></script>
https://www.tiny.cloud/docs/plugins/tinycomments/

<script type="text/javascript">
tinymce.init({
	  selector: 'textarea#full-featured',
	  plugins: 'print preview fullpage powerpaste searchreplace autolink directionality advcode visualblocks visualchars fullscreen image link media mediaembed template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists wordcount tinymcespellchecker a11ychecker imagetools textpattern help formatpainter permanentpen pageembed tinycomments mentions linkchecker',
	  toolbar: 'formatselect | bold italic strikethrough forecolor backcolor permanentpen formatpainter | link image media pageembed | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent | removeformat | addcomment',
	  image_advtab: true,
	  content_css: [
	    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
	    '//www.tiny.cloud/css/codepen.min.css'
	  ],
	  link_list: [
	    { title: 'My page 1', value: 'http://www.tinymce.com' },
	    { title: 'My page 2', value: 'http://www.moxiecode.com' }
	  ],
	  image_list: [
	    { title: 'My page 1', value: 'http://www.tinymce.com' },
	    { title: 'My page 2', value: 'http://www.moxiecode.com' }
	  ],
	  image_class_list: [
	    { title: 'None', value: '' },
	    { title: 'Some class', value: 'class-name' }
	  ],
	  importcss_append: true,
	  height: 400,
	  file_picker_callback: function (callback, value, meta) {
	    /* Provide file and text for the link dialog */
	    if (meta.filetype === 'file') {
	      callback('https://www.google.com/logos/google.jpg', { text: 'My text' });
	    }

	    /* Provide image and alt text for the image dialog */
	    if (meta.filetype === 'image') {
	      callback('https://www.google.com/logos/google.jpg', { alt: 'My alt text' });
	    }

	    /* Provide alternative source and posted for the media dialog */
	    if (meta.filetype === 'media') {
	      callback('movie.mp4', { source2: 'alt.ogg', poster: 'https://www.google.com/logos/google.jpg' });
	    }
	  },
	  templates: [
	    { title: 'Some title 1', description: 'Some desc 1', content: 'My content' },
	    { title: 'Some title 2', description: 'Some desc 2', content: '<div class="mceTmpl"><span class="cdate">cdate</span><span class="mdate">mdate</span>My content2</div>' }
	  ],
	  template_cdate_format: '[CDATE: %m/%d/%Y : %H:%M:%S]',
	  template_mdate_format: '[MDATE: %m/%d/%Y : %H:%M:%S]',
	  image_caption: true,
	  spellchecker_dialog: true,
	  spellchecker_whitelist: ['Ephox', 'Moxiecode'],
	  tinycomments_mode: 'embedded',

	  content_style: '.mce-annotation { background: #fff0b7; } .tc-active-annotation {background: #ffe168; color: black; }'
	 });

</script>
</head>
<body>

<label for="toggleBootstrap">Toggle Bootstrap <input type="checkbox" id="toggleBootstrap" /></label> |
  dataType: <label>XML <input type="radio" name="demo-dataType" value="xml" class="demo-dataType" /></label><label>JSON <input type="radio" name="demo-dataType" value="json" class="demo-dataType" /></label>
  <div class="content">
    
    <h1 class="formrender-title">jQuery formRender -
      <button class="editForm" onclick='updateTinymce()'>Edit</button>
    </h1>
 <div class="build-wrap"></div>
    <form class="render-wrap"></form>
    <div class="action-buttons formbuilder-actions formrender-actions">
      <h2>Actions</h2>
	   <button id="loadUserForm" type="button">Load User Form</button>
      <button id="getFieldTypes" type="button">Get Field Types</button>
      <button id="showData" type="button">Show Data</button>
      <button id="clearFields" type="button">Clear All Fields</button>
      <button id="getData" type="button">Get Data</button>
      <button id="getXML" type="button">Get HTML Data</button>
      <button id="getJSON" type="button">Get JSON Data</button>
      <button id="getJS" type="button">Get JS Data</button>
      <button id="setData" type="button">Set Data</button>
      <button id="toggleAllEdit" type="button">Toggle All Field Edit</button>
      <button id="toggleEdit" type="button">Toggle Field Edit</button>
      <input type="text" placeholder="current field id" id="currentFieldId" />
      <button id="addField" type="button">Add Field</button>
      <button id="removeField" type="button">Remove Field</button>
      <button id="resetDemo" type="button">Reset Demo</button>
      <h2>i18n</h2>
      <select id="setLanguage">
        
        <option value="ar-SA">سعودي</option>
        
        <option value="ar-TN">تونسي</option>
        
        <option value="cs-CZ">čeština</option>
        
        <option value="de-DE">Deutsch</option>
        
        <option value="en-US">English (US)</option>
        
        <option value="es-ES">español</option>
        
        <option value="fa-IR">فارسى</option>
        
        <option value="fi-FI">suomen</option>
        
        <option value="fr-FR">français</option>
        
        <option value="he-IL">עברית‬</option>
        
        <option value="hu-HU">magyar</option>
        
        <option value="it-IT">italiano</option>
        
        <option value="ja-JP">日本語</option>
        
        <option value="my-MM">ဗမာစကာ</option>
        
        <option value="nb-NO">norsk</option>
        
        <option value="nl-NL">Nederlands</option>
        
        <option value="pl-PL">Polska</option>
        
        <option value="pt-BR">português</option>
        
        <option value="qz-MM">ဗမာစကာ</option>
        
        <option value="ro-RO">român</option>
        
        <option value="ru-RU">русский язык</option>
        
        <option value="sl-SI">Slovenščina (SI)</option>
        
        <option value="tr-TR">Türkçe</option>
        
        <option value="uk-UA">українська мова</option>
        
        <option value="vi-VN">tiếng Việt</option>
        
        <option value="zh-CN">简化字</option>
        
        <option value="zh-TW">台語</option>
        
      </select>
    </div>
	 <button id="loadUserForm" type="button">Load User Form</button>
	  <button id="loaddata" type="button">Load Default Data</button>
    <div class="action-buttons formrender-actions">
      <h2>Actions</h2>
     
      <button id="showUserData" type="button">Show User Data</button>
      <button id="renderUserForm" type="button">Render User Form</button>
      <button id="getHTML" type="button">Get HTML</button>
      <button id="clearUserForm" type="button">Clear Form</button>
      <button id="testSubmit" type="submit">Test Submit</button>
    </div>
  </div>
    <div id="formbuilder-options"></div>
	
	<form action="updatetemplate" method="post" id="myForm">
	<label for="formname">Enter the Name of the Form</label>
	<input type="text" name="formname" class="formname" id="formname"/>
	<input type="hidden" name="metadata" class="metadata" value="metadata" id="metadata"/>
	<input type="hidden" name="finalHtml" class="finalHtml" value-"finalHtml" id="finalHtml"/>
	<br><p></p>
		<textarea id="full-featured" name="full-featured">
		</textarea>
		<br><br>
		 <button id="submit" onclick='updateHtml()' type="button">submit</button>



	</form>
  <br><br><p></p><br><br><p></p>

  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 
 

  <script src="dist/form-builder.min.js"></script>
  <script src="dist/form-render.min.js"></script>
    <script src="dist/control_plugins/starRating.min.js"></script>
  <script src="dist/control_plugins/textarea.trumbowyg.min.js"></script>
    <script src="assets/js/demo.min.js"></script>
   <script type="text/javascript" >
  
let formData = '[{"type": "header", "subtype": "h1", "label": "BUSINESSS LICENCEE REMINDERS", "className": "Header center"},{"type": "paragraph", "subtype": "p", "label": "Paragraph"},{"type": "text", "label": "Office Use – File", "className": "form-control", "name": "text-1559667311566", "subtype": "text"},{"type": "header", "subtype": "h3", "label": "Attention Business Owner(s):"},{"type": "text", "label": "Business Name:", "className": "form-control", "name": "text-1559667483209", "subtype": "text"},{"type": "text", "label": "Address/Location of the Business:", "className": "form-control", "name": "text-1559667543234", "subtype": "text"},{"type": "date", "label": "(date)", "className": "form-control", "name": "date-1559667674135"},{"type": "text", "label": "Municipal Law Enforcement Officer Name", "className": "form-control under signature field the label", "name": "text-1559667696397", "subtype": "text"},{"type": "text", "label": "Phone Number", "className": "form-control", "name": "text-1559667815599", "subtype": "text"},{"type": "text", "label": "Municipal Law Enforcement Officer Signature", "className": "form-control", "name": "text-1559675549246", "subtype": "text"},{"type": "date", "label": "Date", "className": "form-control", "name": "date-1559675593103"},{"type": "checkbox-group", "label": "Hand delivered to", "name": "checkbox-group-1559675646771","values":[{"label":"Street Sweeper","value":"option-1","selected":true},{"label":"Moth Man","value":"option-2"},{"label":"Chemist","value":"option-3"}]},{"type": "header", "subtype": "h3", "label": "BUILDING A BETTER COMMUNITY"}]';


window.sessionStorage.setItem("formDat",formData);

//$( "#results span:first" ).text( len );
//tinyMCE.activeEditor.setContent(html);
//tinymce.get("full-featured").setContent(html);
function updateTinymce(){
	setTimeout(function(){
	let activeEditor = tinymce.get('full-featured');
	if($(".render-wrap").is(":visible")){
		console.log(' render visible');
		const html = $('.render-wrap').formRender('html');
		$('.render-wrap').formRender('html');
		if(activeEditor!==null){
			activeEditor.setContent(html);
		} else {
			$('#full-featured').val(html);
		}
	}
	},4000);
}
function updateHtml(){
	if($(".render-wrap").is(":hidden")){
		window.sessionStorage.setItem('finalhtml','');
		alert('Click on Render button');return;
	}if(!$(".formname").val()){
		window.sessionStorage.setItem('finalhtml','');
		alert('Enter Form Name');return;
	}
	
	  const html = tinymce.get('full-featured').getContent();
	  console.log(' final :'+html);
	  window.sessionStorage.setItem('finalhtml',html);
  }

   </script>	

	  <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.1/jquery.rateyo.min.js"></script>

</body>
</html>