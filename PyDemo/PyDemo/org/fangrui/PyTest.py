'''
Created on 2019��2��2��

@author: admin
'''
from fabric import Connection
def main():
    # ip ����������
    # �����ĵ�������ssh�������¼���Ͳ���Ҫ connect_kwargs ��ָ�������ˡ�
    c = Connection("root@232.231.231.22", connect_kwargs={"password": "youpassword"})
    with c.cd('/var/www/youproject'):
        c.run("git pull origin master")
        c.run("/usr/bin/supervisorctl -c ../supervisor/supervisord.conf restart youproject")
if __name__ == '__main__':
     main()