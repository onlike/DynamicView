<h1>DynamicView</h1>
<p>a simple frame of UI dynamic wtih android develop.</p>
<ul>
<li>server controll mobile applications UI style.</li>
<li>percentage measure to keep Android and IOS different devices display uniformity.</li>
<li>support preload to more fast display.</li>
<li>cache template to prevent duplicate parsing.</li>
</ul>

<h2>Screenshots</h2>
<img src = "https://github.com/onlike/DynamicView/blob/master/screenshot/screenshot.gif" title = "sample screenshots"/>

<h2>1.How to use?</h2>

<h3>a : import dynamic view library</h3>
<p>at now, just provide libaray mode to import.</p>
<pre>
implementation project(path: ':dynamic_view')
</pre>

<h3> b : init dynamic view</h3>
<p>must init it in your application.</p>
<pre>
DynamicMaster.init(this);
</pre>

<h3> c : load template</h3>
<p>support preload template when you want to more fast display.</p>
<p><b>preload :</b></p>
<pre>
DynamicMaster.preload().injectViewTemplate(Map&lt;String, Object&gt; viewData);
</pre>

<p><b>defload :</b></p>
<pre>
DynamicView dynamicView = DynamicMaster.get(Context context)
	.injectViewData(Map&lt;String, Object&gt; viewData)
	.injectPropertiesData(Map&lt;String, Object&gt; propertiesData)
	.build();
</pre>

<h3> d : obtain root view and display in window</h3>
<p>it's dynamic load,so you must prepare a display container.</p>
<pre>
View templateRootView = dynamicView.bindView();

contentContainer.addView(rootView);
</pre>

<h3> e : bind data</h3>
<p>support inject a callback with binding.</p>
<p><b>default binding :</b></p>
<pre>
dynamicView.bindData();
</pre>

<p><b>binding with callback :</b></p>
<ol>
<li>
<p><b>origin callback,it will be return origin data</b></p>
<pre>
dynamicView.bindData(IViewBindCallback bindCallback);
</pre>
</li>

<li>
<p><b>simple callback,just handle different view bind</b></p>
<pre>
dynamicView.bindData(ViewBindCallbackAdapter vbcAdapter);
</pre>
</li>
</ol>


<h3> f : monitor interface interactive</h3>
<pre>
dynamicView.bindEventTouch(IEventTouchCallback eventTouchCallback);
</pre>


<h2>2.How to create template ?</h2>

<h3>a : template composition</h3>
<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<th rowspan="2">template</th>
<td>viewTree</td>
</tr>
<tr>
<td>treeTag</td>
</tr>

<tr>
<th rowspan="2">properties</th>
<td>data</td>
</tr>
<tr>
<td>event</td>
</tr>
</table>

<ul>
<li>viewTree : view properties set.</li>
<li>treeTag : template identifier.</li>
<li>data : properties corresponding to view,use <b>viewTag</b> as a differentiator.</li>
<li>event : same as data.</li>
</ul>

<p>eg : </p>
<pre>
{
  "template": {
    "viewTree": {},
    "treeTag": ""
  },
  "properties": {
    "data": {},
    "event": {}
  }
}
</pre>

<h3>b : template details</h3>
<table border="1" cellspacing="0" cellpadding="10" >
<td colspan="3" align="center">
<b>VirtualView</b>
</td>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td>tag</td>
<td align="center">String</td>
<td><b>viewTag,unique in template</b></td>
</tr>

<tr>
<td>type</td>
<td align="center">int</td>
<td align="left">
<b>view type</b>
<br/>
1001 : text
<br/>
1002 : image
<br/>
1003 : frame
<br/>
1004 : linear
</td>
</tr>

<tr>
<td>width</td>
<td align="center">double</td>
<td align="left">
<b>view width</b>
<br/>
-1 : wrap of self
<br/>
-2 : full in container
<br/>
range(0, 100) : percent of screen width

</td>
</tr>

<tr>
<td>height</td>
<td align="center">double</td>
<td align="left">
<b>view height</b>
<br/>
-1 : wrap of self
<br/>
-2 : full in container
<br/>
range(0, 100) : percent of screen height

</td>
</tr>

<tr>
<td>selfGravity</td>
<td align="center">int</td>
<td align="left">
<b>view gravity of slef or child</b>
<br/>
3101 : top
<br/>
3102 : left
<br/>
3103 : bottom
<br/>
3104 : right
<br/>
3105 : center
<br/>
3106 : center_vertical
<br/>
3107 : center_horizontal
</td>
</tr>

<tr>
<td>layoutGravity</td>
<td align="center">int</td>
<td align="left">
<b>view gravity of container</b>
<br/>
3101 : top
<br/>
3102 : left
<br/>
3103 : bottom
<br/>
3104 : right
<br/>
3105 : center
<br/>
3106 : center_vertical
<br/>
3107 : center_horizontal
</td>
</tr>

<tr>
<td>topMargin</td>
<td align="center">double</td>
<td align="left">
<b>top margin of container</b>
<br/>
range(0, 100) : percent of screen height
</td>
</tr>

<tr>
<td>leftMargin</td>
<td align="center">double</td>
<td align="left">
<b>left margin of container</b>
<br/>
range(0, 100) : percent of screen width
</td>
</tr>

<tr>
<td>rightMargin</td>
<td align="center">double</td>
<td align="left">
<b>right margin of container</b>
<br/>
range(0, 100) : percent of screen width
</td>
</tr>

<tr>
<td>bottomMargin</td>
<td align="center">double</td>
<td align="left">
<b>bottom margin of container</b>
<br/>
range(0, 100) : percent of screen height
</td>
</tr>

<tr>
<td>topPadding</td>
<td align="center">double</td>
<td align="left">
<b>top padding of self bound</b>
<br/>
range(0, 100) : percent of screen height
</td>
</tr>

<tr>
<td>leftPadding</td>
<td align="center">double</td>
<td align="left">
<b>left padding of self bound</b>
<br/>
range(0, 100) : percent of screen width
</td>
</tr>

<tr>
<td>rightPadding</td>
<td align="center">double</td>
<td align="left">
<b>right padding of self bound</b>
<br/>
range(0, 100) : percent of screen width
</td>
</tr>

<tr>
<td>bottomPadding</td>
<td align="center">double</td>
<td align="left">
<b>bottom padding of self bound</b>
<br/>
range(0, 100) : percent of screen height
</td>
</tr>

<tr>
<td>bgColor</td>
<td align="center">String</td>
<td>
<b>view background color</b>
<br/>
hexadecimal color
</td>
</tr>

<tr>
<td>parentType</td>
<td align="center">int</td>
<td align="left">
<b>container type</b>
<br/>
1003 : frame
<br/>
1004 : linear
</td>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>Text</b> extends VirtualView
</td>
</tr>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td>fontName</td>
<td align="center">String</td>
<td align="left">
<b>local font resources</b>
</tr>

<tr>
<td>textColor</td>
<td align="center">String</td>
<td align="left">
<b>text content color</b>
</tr>

<tr>
<td>textSize</td>
<td align="center">int</td>
<td align="left">
<b>base of sp</b>
</tr>

<tr>
<td>maxLines</td>
<td align="center">int</td>
<td align="left">
<b>end ellipsis after out of range</b>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>Image</b> extends VirtualView
</td>
</tr>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td>scaleType</td>
<td align="center">int</td>
<td align="left">
<b>image clipping mode</b>
<br/>
3301 : scale center
<br/>
3302 : scale fit
</td>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>Container</b> extends VirtualView
</td>
</tr>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td>child</td>
<td align="center">List&lt;? extends VirtualView&gt;</td>
<td align="left">
<b>child type must be a subclass of VirtualView</b>
</td>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>Linear</b> extends Container
</td>
</tr>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td>orientation</td>
<td align="center">int</td>
<td align="left">
<b>place orientation</b>
<br/>
3201 : vertical
<br/>
3202 : horizontal
</td>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>Frame</b> extends Container
</td>
</tr>

<tr>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>
</table>

<p>eg: </p>
<pre>
{
  "tag": "linear_1",
  "type": 1004,
  "width": -2,
  "height": -1,
  "selfGravity": 0,
  "layoutGravity": 0,
  "topMargin": 0.5,
  "leftMargin": 2,
  "bottomMargin": 0.5,
  "rightMargin": 2,
  "bgColor": "",
  "parentType": 1003,
  "orientation": 3201,
  "child": [
    {
      "tag": "text_1",
      "type": 1001,
      "width": -2,
      "height": -1,
      "selfGravity": 0,
      "layoutGravity": 0,
      "topMargin": 0,
      "leftMargin": 0,
      "bottomMargin": 0,
      "rightMargin": 0,
      "bgColor": "",
      "parentType": 1004,
      "fontName": "",
      "textColor": "#000000",
      "textSize": 18,
      "maxLines": 2
    },
    {
      ....
    },
    {
      "tag": "frame_1",
      "type": 1003,
      "width": -2,
      "height": 0.08,
      "selfGravity": 0,
      "layoutGravity": 0,
      "topMargin": 1,
      "leftMargin": 0,
      "bottomMargin": 0,
      "rightMargin": 0,
      "parentType": 1004,
      "bgColor": "#999999",
      "child": [...]
    }
  ]
}
</pre>

<h3>c : properties details</h3>
<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="4" align="center">
<b>data</b>
</td>
</tr>

<tr>
<th>viewTag</th>
<th>name</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td align="center">&lt; ? &gt;<br/><b>(same of target view's tag properties)</b></td>
<td>textContent</td>
<td align="center">String</td>
<td align="left">
<b>text content</b>
</td>
</tr>

<tr>
<td align="center">&lt; ? &gt;<br/><b>(same of target view's tag properties)</b></td>
<td>imgSource</td>
<td align="center">String</td>
<td align="left">
<b>image resource</b>
<br/>
1.local icon resources(icon name)
<br/>
2.hexadecimal color
<br/>
3.server image resources(http | | https)
</td>
</tr>
</table>
<br/>

<table border="1" cellspacing="0" cellpadding="10" >
<tr>
<td colspan="3" align="center">
<b>event</b>
</td>
</tr>

<tr>
<th>viewTag</th>
<th>type</th>
<th>value</th>
</tr>

<tr>
<td align="center">&lt; ? &gt;<br/><b>(same of target view's tag properties)</b></td>
<td align="center">List&lt;String&gt;</td>
<td align="left">
<b>custom rules to user touch callback</b>
</td>
</tr>
</table>

<p>eg: </p>
<pre>
{
  "data": {
    "text_1": {
      "textContent": ""
    },
    "text_2": {
      "textContent": ""
    },
    "text_3": {
      "textContent": ""
    },
    "image_1": {
      "imgSource": ""
    }
  },
  "event": {
    "linear_1": [
      "dyv:statistics",
      "dyv:navigationDetails"
    ],
    "image_1": [
      "dyv:toast"
    ]
  }
}
</pre>

<h3>d : final assemble</h3>
<ol>
<li>
subclass of VirtualView assembale viewTree.
</li>

<li>
viewTree and treeTag assembale template.
</li>

<li>
data and event assemble properties.
</li>
</ol>

<p>eg: </p>
<pre>
{
  "template": {
    "viewTree": {
      "tag": "linear_1",
      "type": 1004,
      "width": -2,
      "height": -1,
      "selfGravity": 0,
      "layoutGravity": 0,
      "topMargin": 0.5,
      "leftMargin": 2,
      "bottomMargin": 0.5,
      "rightMargin": 2,
      "bgColor": "",
      "parentType": 1003,
      "orientation": 3201,
      "child": [
        {
          "tag": "text_1",
          "type": 1001,
          "width": -2,
          "height": -1,
          "selfGravity": 0,
          "layoutGravity": 0,
          "topMargin": 0,
          "leftMargin": 0,
          "bottomMargin": 0,
          "rightMargin": 0,
          "bgColor": "",
          "parentType": 1004,
          "fontName": "",
          "textColor": "#000000",
          "textSize": 18,
          "maxLines": 2
        },
        {
          "tag": "text_2",
          "type": 1001,
          "width": -2,
          "height": -2,
          "selfGravity": 3103,
          "layoutGravity": 0,
          "topMargin": 0,
          "leftMargin": 0,
          "bottomMargin": 0,
          "rightMargin": 0,
          "bgColor": "",
          "parentType": 1004,
          "fontName": "",
          "textColor": "#999999",
          "textSize": 14,
          "maxLines": 1
        },
        {
          "tag": "frame_1",
          "type": 1003,
          "width": -2,
          "height": -1,
          "selfGravity": 0,
          "layoutGravity": 0,
          "topMargin": 1,
          "leftMargin": 0,
          "bottomMargin": 1,
          "rightMargin": 0,
          "parentType": 1003,
          "orientation": 3202,
          "child": [
            {
              "tag": "image_1",
              "type": 1002,
              "width": -2,
              "height": 28,
              "selfGravity": 0,
              "layoutGravity": 0,
              "topMargin": 0,
              "leftMargin": 0,
              "bottomMargin": 0,
              "rightMargin": 0,
              "bgColor": "#999999",
              "parentType": 1003,
              "scaleType": 3301
            },
            {
              "tag": "text_3",
              "type": 1001,
              "width": -1,
              "height": -1,
              "selfGravity": 3103,
              "layoutGravity": 0,
              "topMargin": 24,
              "leftMargin": 3,
              "bottomMargin": 0,
              "rightMargin": 3,
              "bgColor": "#75000000",
              "topPadding": 0.3,
              "leftPadding": 2,
              "bottomPadding": 0.3,
              "rightPadding": 2,
              "parentType": 1003,
              "scaleType": 3301,
              "fontName": "",
              "textColor": "#eeeeee",
              "textSize": 14,
              "maxLines": 0
            }
          ]
        },
        {
          "tag": "linear_3",
          "type": 1004,
          "width": -2,
          "height": 0.05,
          "selfGravity": 0,
          "layoutGravity": 0,
          "topMargin": 0,
          "leftMargin": 0,
          "bottomMargin": 0,
          "rightMargin": 0,
          "parentType": 1004,
          "orientation": 3201,
          "bgColor": "#999999",
          "child": []
        }
      ]
    },
    "treeTag": "big_image"
  },
  "properties": {
    "data": {
      "text_1": {
        "textContent": "dynamic view"
      },
      "text_2": {
        "textContent": "bottom text"
      },
      "text_3": {
        "textContent": "label text"
      },
      "image_1": {
        "imgSource": "https://"
      }
    },
    "event": {
      "linear_1": [
        "dyv:statistics",
        "dyv:navigationDetails"
      ],
      "image_1": [
        "dyv:toast"
      ]
    }
  }
}
</pre>
<h2>3.TODO</h2>
<ul>
<li>support maven center to import.</li>
<li>support more view.</li>
<li>enum properties support bitwise.</li>
<li>measure mode support percentage and specific value switching with template.</li>
<li>template content compression.</li>
<li>need a template editor and preview tools.</li>
</ul>

<h2>4.Document Resource</h2>
<ul>
<li><a href="https://github.com/onlike/DynamicView/tree/master/document">docs</a></li>
</ul>
