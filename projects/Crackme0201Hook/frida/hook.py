import frida, sys

jscode = '''
Java.perform(function () {
    //这里是要Hook的软件具体位置的   包名 + 类名
    var mainActivity = Java.use('com.neo.crackme0201.MainActivity');
    //这里写要Hook的方法名  以及参数
    mainActivity.checkSn.implementation=function(str){
        //直接返回为true
        return true;
    };
});
'''


def on_message(message, data):
    print(message)

process = frida.get_usb_device().attach('com.neo.crackme0201')  # 要Hook的软件包名
script = process.create_script(jscode)
script.on('message', on_message)
print('运行完毕！！！请随便输入密码进入软件。')
script.load()
sys.stdin.read()