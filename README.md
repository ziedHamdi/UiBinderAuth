UiBinderAuth
============

GWT UiBinder authorization layer : a proxy that give you control over the creation of each widget

(this project was first shared on google code : https://code.google.com/p/ui-binder-factory-proxy/)

What is it for?
UiBinder? is a great way to isolate ui design code and ui logic. It unfortunately doesn't permit to declare authorization rules.

That's the purpose of this library that is a fork of GWT UiBinder?: you can decide to intercept the creation of widgets declared in UiBinder? to change their look, hide them, add handlers, etc...

You can also cache Widget instances for later reuse.

Why is it needed?
UiBinder? describes well how fields have to display, but it doesn't include any profile mechanism.

With this library you can put all the widgets of all the possible scenarios in a screen, and depending on the connected profile, you can hide some widgets, use personalized styles, add handlers, listen to event bus, and more.

The current version doesn't support holding the created widget references. So currently, if a new user connects (or an application wide information changes), you need to reload the page. It's okay since if a person disconnects it's a big enough operation to reload the page.

But in future versions, a Widget pool cache will be provided. This will allow dynamic changes to be reflected on widgets: for example when the person will look at a profile page (let's say in a social network), all widgets could change look because the Place specifies it's looking at a profile.

What does it do?
This library permits to :

control widgets at creation time (the xml file and the field name are passed as arguments)
modify the declarations stated in UiBinder? xml files (at creation + on event bus events)
add handlers to widgets (at creation + on event bus events)
not intrusive to your existing architecture: plugged from the outside
This library intercepts the request to create new widgets, it has detailed information about the widget (UiBinder? file + field name). It may therefore be used to recycle widgets in a pool

What does it not do?
Since problems can be solved in many ways, this library focuses on the widget creation subject only. It doesn't provide any services after creation like checking the states of the widget tree. That can be done in different manners, and we prefer not to choose one on your behalf

The objective of the library being non intrusive is a key concept. Therefore, a different project will propose a way to cache widgets and another one will propose to register handlers to recycle widgets and handle them during their life cycle.

Where will it evolve
The UiBinder? generates too many code that is hard to debug since it is text that has to be compiled. This library will gradually replace parts of the generated "hard coded text" by extensible calls to compiled code (that can be separately unit tested).

For now it is done only for after <g:Xwidget > initialization is done : the init() method.

But all what happens between can gradually be moved to compiled code: attached event handles, styleName css setting. Also direct accessor calls like <g:TextBox text="meHere"> can be proxied to be interceptable. The generated code will do something like:

  if( proxy.canCall( fieldName ) ) 
    field.setText(); 

Where should I start?
clone this library and get started: https://github.com/ziedHamdi/UiBinderAutho/blob/master/README.md
