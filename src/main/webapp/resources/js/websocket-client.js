/**
 * Created by readlearncode.com on 29/11/2016.
 */
var wsocket;
var serviceLocation = "ws://localhost:8080/dukechat/chat/";
var $nickName;
var $message;
var $chatWindow;
var $alert;
var room = '';

function onMessageReceived(evt) {

    var msg = JSON.parse(evt.data); // native API

    var $messageLine = $('<tr><td class="received">' + msg.received
        + '</td><td class="user label label-info">' + msg.sender
        + '</td><td class="message badge">' + msg.content
        + '</td></tr>');
    $chatWindow.append($messageLine);
}

function sendMessage() {
    var msg = '{"content":"' + $message.val() + '", "sender":"'
        + $nickName.val() + '", "received":""}';
    wsocket.send(msg);
    $message.val('').focus();
}

function connectToChatserver() {
    room = $('#chatroom option:selected').val();
    nickName = $('#nickname').val();
    wsocket = new WebSocket(serviceLocation + room);
    // wsocket = new WebSocket(serviceLocation + "/?sender=" +  encodeURI(nickName));
    wsocket.onerror = onConnectionError;
    wsocket.onmessage = onMessageReceived;
}

function onConnectionError(evt) {

    var msg = evt; // native API
    var $messageLine = $(evt);
    $chatWindow.append($messageLine);

}

function leaveRoom() {
    wsocket.close();
    $chatWindow.empty();
    $('.chat-wrapper').hide();
    $('.chat-signin').show();
    $nickName.focus();
}

$(document).ready(function () {
    $nickName = $('#nickname');
    $message = $('#message');
    $chatWindow = $('#response');
    $alert = $('#alert');
    $('.chat-wrapper').hide();
    $nickName.focus();

    $('#enterRoom').click(function (evt) {
        evt.preventDefault();
        connectToChatserver();
        $('.chat-wrapper h2').text('Chat # ' + $nickName.val() + "@" + room);
        $('.chat-signin').hide();
        $('.chat-wrapper').show();
        $message.focus();
    });
    $('#do-chat').submit(function (evt) {
        evt.preventDefault();
        sendMessage()
    });

    $('#leave-room').click(function () {
        leaveRoom();
    });
});

