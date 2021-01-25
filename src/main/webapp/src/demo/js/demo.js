import '../sass/demo.scss'
import { insertStyle, removeStyle } from '../../js/utils'
import { builderActions, renderActions, demoActions } from './actionButtons'

const localeSessionKey = 'formBuilder-locale'
const defaultLocale = 'en-US'
const formData = '[{"type":"text","label":"Full Name","subtype":"text","className":"form-control","name":"text-1476748004559"},{"type":"select","label":"Occupation","className":"form-control","name":"select-1476748006618","values":[{"label":"Street Sweeper","value":"option-1","selected":true},{"label":"Moth Man","value":"option-2"},{"label":"Chemist","value":"option-3"}]},{"type":"textarea","label":"Short Bio","rows":"5","className":"form-control","name":"textarea-1476748007461"}]';

const businessLicenseReminderJson = '[{"type": "header", "subtype": "h1", "label": "BUSINESS LICENCE REMINDER", "className": "Header center"},{"type": "paragraph", "subtype": "p", "label": "Paragraph"},{"type": "text", "label": "Office Use – File", "className": "form-control", "name": "text-1559667311566", "subtype": "text"},{"type": "header", "subtype": "h3", "label": "Attention Business Owner(s):"},{"type": "text", "label": "Business Name:", "className": "form-control", "name": "text-1559667483209", "subtype": "text"},{"type": "text", "label": "Address/Location of the Business:", "className": "form-control", "name": "text-1559667543234", "subtype": "text"},{"type": "date", "label": "(date)", "className": "form-control", "name": "date-1559667674135"},{"type": "text", "label": "Municipal Law Enforcement Officer Name", "className": "form-control under signature field the label", "name": "text-1559667696397", "subtype": "text"},{"type": "text", "label": "Phone Number", "className": "form-control", "name": "text-1559667815599", "subtype": "text"},{"type": "text", "label": "Municipal Law Enforcement Officer Signature", "className": "form-control", "name": "text-1559675549246", "subtype": "text"},{"type": "date", "label": "Date", "className": "form-control", "name": "date-1559675593103"},{"type": "checkbox-group", "label": "Hand delivered to", "name": "checkbox-group-1559675646771","values":[{"label":"Street Sweeper","value":"option-1","selected":true},{"label":"Moth Man","value":"option-2"},{"label":"Chemist","value":"option-3"}]},{"type": "header", "subtype": "h3", "label": "BUILDING A BETTER COMMUNITY"}]';
window.sessionStorage.setItem('formData',businessLicenseReminderJson);
const dataTypes = document.querySelectorAll('.demo-dataType')
const dataType = window.sessionStorage.getItem('dataType') || 'json'
const changeDataType = ({ target }) => {
  window.sessionStorage.setItem('dataType', target.value)
  demoActions.resetDemo()
}
for (let i = 0; i < dataTypes.length; i++) {
  if (dataType === dataTypes[i].value) {
    dataTypes[i].checked = true
  }
  dataTypes[i].addEventListener('click', changeDataType, false)
}

const toggleBootStrap = ({ target }) => {
  if (!target.checked) {
    removeStyle('bootstrap')
  } else {
    insertStyle({
      src: 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css',
      id: 'bootstrap',
    })
  }
}

document.getElementById('toggleBootstrap').addEventListener('click', toggleBootStrap, false)

jQuery(function($) {
  const fields = [
    {
      type: 'autocomplete',
      label: 'Custom Autocomplete',
      required: true,
      values: [
        { label: 'SQL' },
        { label: 'C#' },
        { label: 'JavaScript' },
        { label: 'Java' },
        { label: 'Python' },
        { label: 'C++' },
        { label: 'PHP' },
        { label: 'Swift' },
        { label: 'Ruby' },
      ],
    },
    {
      label: 'Star Rating',
      attrs: {
        type: 'starRating',
      },
      icon: '🌟',
    },
  ]

  const replaceFields = [
    {
      type: 'textarea',
      subtype: 'tinymce',
      datatype: 'custom-tinymce',
      label: 'tinyMCE',
      required: true,
    },
  ]

  const actionButtons = [
    {
      id: 'smile',
      className: 'btn btn-success',
      label: '😁',
      type: 'button',
      events: {
        click: () => {
          // @todo toggle options editor instead
          alert('😁😁😁 !SMILE! 😁😁😁')
        },
      },
    },
    'save',
  ]

  const templates = {
    starRating: function(fieldData) {
      return {
        field: '<span id="' + fieldData.name + '">',
        onRender: () => {
          $(document.getElementById(fieldData.name)).rateYo({ rating: 3.6 })
        },
      }
    },
  }

  const inputSets = [
    {
      label: 'User Details',
      icon: '👨',
      name: 'user-details', // optional
      showHeader: true, // optional
      fields: [
        {
          type: 'text',
          label: 'First Name',
          className: 'form-control',
        },
        {
          type: 'select',
          label: 'Profession',
          className: 'form-control',
          values: [
            {
              label: 'Street Sweeper',
              value: 'option-2',
              selected: false,
            },
            {
              label: 'Brain Surgeon',
              value: 'option-3',
              selected: false,
            },
          ],
        },
        {
          type: 'textarea',
          label: 'Short Bio:',
          className: 'form-control',
        },
      ],
    },
    {
      label: 'User Agreement',
      fields: [
        {
          type: 'header',
          subtype: 'h3',
          label: 'Terms & Conditions',
          className: 'header',
        },
        {
          type: 'paragraph',
          label:
            'Leverage agile frameworks to provide a robust synopsis for high level overviews. Iterative approaches to corporate strategy foster collaborative thinking to further the overall value proposition. Organically grow the holistic world view of disruptive innovation via workplace diversity and empowerment.',
        },
        {
          type: 'paragraph',
          label:
            'Bring to the table win-win survival strategies to ensure proactive domination. At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution. User generated content in real-time will have multiple touchpoints for offshoring.',
        },
        {
          type: 'checkbox',
          label: 'Do you agree to the terms and conditions?',
        },
      ],
    },
  ]

  const typeUserDisabledAttrs = {
    autocomplete: ['access'],
  }

  const typeUserAttrs = {
    text: {
      shape: {
        label: 'Class',
        multiple: true,
        options: {
          'red form-control': 'Red',
          'green form-control': 'Green',
          'blue form-control': 'Blue',
        },
        style: 'border: 1px solid red',
      },
      readonly: {
        label: 'readonly',
        value: false,
      },
    },
    number: {
      volume: {
        label: 'Volume Level',
        value: 1,
        max: 11,
      },
    },
  }

  // test disabledAttrs
  const disabledAttrs = ['placeholder', 'name']

  const fbOptions = {
    disabledSubtypes: {
      text: ['password'],
    },
    // disableHTMLLabels: true,
    disabledAttrs,
    // allowStageSort: false,
    dataType,
    subtypes: {
      text: ['datetime-local'],
    },
    onSave: toggleEdit,
    onAddField: fieldId => {
      document.getElementById('currentFieldId').value = fieldId
    },
    onClearAll: () => window.sessionStorage.removeItem('formData'),
    stickyControls: {
      enable: true,
    },
    sortableControls: true,
    fields: fields,
    templates: templates,
    inputSets: inputSets,
    typeUserDisabledAttrs: typeUserDisabledAttrs,
    typeUserAttrs: typeUserAttrs,
    disableInjectedStyle: false,
    actionButtons: actionButtons,
    disableFields: ['autocomplete', 'custom-tinymce'],
    replaceFields: replaceFields,
    disabledFieldButtons: {
      text: ['copy'],
    },
    controlPosition: 'right', // left|right,
    i18n: {
      override: {
        [defaultLocale]: {
          number: 'Big Numbers',
        },
      },
    },
  }
  const formData = window.sessionStorage.getItem('formData')
  let editing = true

  if (formData) {
    fbOptions.formData = formData
  }

  /**
   * Toggles the edit mode for the demo
   * @return {Boolean} editMode
   */
  function toggleEdit() {
    document.body.classList.toggle('form-rendered', editing)
    if (!editing) {
      $('.build-wrap').formBuilder('setData', $('.render-wrap').formRender('userData'))
    } else {
      const formRenderData = $('.build-wrap').formBuilder('getData', dataType)
      $('.render-wrap').formRender({
        formData: formRenderData,
        templates: templates,
        dataType,
      })
      window.sessionStorage.setItem('formData', formRenderData)
    }
    return (editing = !editing)
  }

  const formBuilder = $('.build-wrap').formBuilder(fbOptions)

  const fbPromise = formBuilder.promise

  fbPromise.then(function(fb) {
    const apiBtns = {
      ...builderActions,
      ...renderActions,
      ...demoActions,
    }

    Object.keys(apiBtns).forEach(function(action) {
      document.getElementById(action).addEventListener('click', function(e) {
        apiBtns[action]()
      })
    })

    document.querySelectorAll('.editForm').forEach(element => element.addEventListener('click', toggleEdit), false)
    const langSelect = document.getElementById('setLanguage')
    const savedLocale = window.sessionStorage.getItem(localeSessionKey)

    if (savedLocale && savedLocale !== defaultLocale) {
      langSelect.value = savedLocale
      fb.actions.setLang(savedLocale)
    }

    langSelect.addEventListener(
      'change',
      ({ target: { value: lang } }) => {
        window.sessionStorage.setItem(localeSessionKey, lang)
        fb.actions.setLang(lang)
      },
      false,
    )
  })
},

 // setInterval(function(){
	  setTimeout(function(){
	 const formdata = window.sessionStorage.getItem('couchdata')
	 console.log(formdata)
	 const request = new XMLHttpRequest()
	 const params = 'property='+formdata
	 request.open('POST', 'http://localhost:8080/SpringWebTemplate/addTemplate', true)
	 request.onreadystatechange = function() {if (request.readyState == 4) console.log('ready')}
	 request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
	 
	 request.setRequestHeader('Access-Control-Allow-Headers', '*')
	 request.send(params)
	/*  $.ajax({
                url: 'http://localhost:8080/SpringWebTemplate/addTemplate',
                dataType: 'text',
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                data: formdata,
                success: function( data, textStatus, jQxhr ){
                    $('#response pre').html( data )
                },
                error: function( jqXhr, textStatus, errorThrown ){
                    console.log( errorThrown )
                }
            }); */
 },5000)
)
