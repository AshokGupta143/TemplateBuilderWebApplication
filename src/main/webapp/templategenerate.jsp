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
</head>
</head>
<body>
<label for="toggleBootstrap">Toggle Bootstrap <input type="checkbox" id="toggleBootstrap" /></label> |
  dataType: <label>XML <input type="radio" name="demo-dataType" value="xml" class="demo-dataType" /></label><label>JSON <input type="radio" name="demo-dataType" value="json" class="demo-dataType" /></label>
 <div class="build-wrap"></div>
    <form class="render-wrap"></form>
    <div class="action-buttons formbuilder-actions formrender-actions">
      <h2>Actions</h2>
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
    <div class="action-buttons formrender-actions">
      <h2>Actions</h2>
      <button id="loadUserForm" type="button">Load User Form</button>
      <button id="showUserData" type="button">Show User Data</button>
      <button id="renderUserForm" type="button">Render User Form</button>
      <button id="getHTML" type="button">Get HTML</button>
      <button id="clearUserForm" type="button">Clear Form</button>
      <button id="testSubmit" type="submit">Test Submit</button>
    </div>
  </div>
  <div id="formbuilder-options"></div>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 
 

  <script src="dist/form-builder.min.js"></script>
  <script src="dist/form-render.min.js"></script>
    <script src="dist/control_plugins/starRating.min.js"></script>
  <script src="dist/control_plugins/textarea.trumbowyg.min.js"></script>
    <script src="assets/js/demo.min.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.1/jquery.rateyo.min.js"></script>
/
</body>
</html>