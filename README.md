ModReq
---
####Introduction
ModReq is a plugin that adds support tickets to your minecraft server. The plugin allows players to submit a ticket and the staff members will be notified about it. Tickets of different priorities will be handled by different ranks of staff members. This means that people higher up the chain only have to deal with higher priority tickets, thus allowing them to focus on just that. 
<a name="introduction"></a>
####Installation
Installing Modreq is quite easy. All you need to do to get the plugin running is to drag and drop the jar-file into your bukkit plugins directory. ModReq will create the database and everything for you. It will also setup some priorities and ranks, but you might want to change them to suit your server, more about that later. Once you booted up the server with ModReq installed you can start submitting and handling tickets via the [commands](#commands). By default OPs have all permissions and normal people only have permission to submit a ticket and comment on them. If you would like a more advanced permissions-setup I would like to refer you to the [permissions section](#permissions).

####Customization
As you might have noticed after installation, ModReq is modular. All the priorities and ranks are defined by the database. Customizing them is quite easy, as you only have 2 options : Via commands and via SQL.
#####Via Commands
Yet to do
#####Via SQL
I would only recommend changing the database via SQL if you know what your are doing. An ER-diagram of the database can be found below and will be our guideline for this section. TO DO