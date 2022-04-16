import frida, sys

jscode = '''
Java.perform(function () {
    //这里是要Hook的软件具体位置的   包名 + 类名
    var mainActivity = Java.use('com.neo.crackme0201.MainActivity');
    //这里写要Hook的方法名  以及参数
    mainActivity.checkSn.implementation=function(username, sn){
        //直接返回为true
        console.log(username, 'username')
        console.log(sn, 'sn')
        send(sn)
        return true;
    };
});
'''


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

process = frida.get_usb_device().attach('Crackme0201')  # 要Hook的软件包名
script = process.create_script(jscode)
script.on('message', on_message)
print('hook 成功。')
script.load()
sys.stdin.read()