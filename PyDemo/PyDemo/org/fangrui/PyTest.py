'''
Created on 2019年2月2日

@author: admin
'''
from fabric import Connection
def main():
    # ip 我是随便填的
    # 如果你的电脑配了ssh免密码登录，就不需要 connect_kwargs 来指定密码了。
    c = Connection("root@232.231.231.22", connect_kwargs={"password": "youpassword"})
    with c.cd('/var/www/youproject'):
        c.run("git pull origin master")
        c.run("/usr/bin/supervisorctl -c ../supervisor/supervisord.conf restart youproject")
if __name__ == '__main__':
     main()