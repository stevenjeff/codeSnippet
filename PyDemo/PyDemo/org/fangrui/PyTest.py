'''
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

def pythonic():
    #  列表推导式
    chars = [c for c in 'python']
    print(chars)
    #字典推导式
    dicts ={'a':1,'b':2,'c':3,'d':4,'e':5}
    newDict ={k:v*3 for (k,v) in dicts.items()}
    print(newDict)
    #集合推导式
    sets = {1,2,3,4,5,6,7}
    new_sets = {i*4 for i in sets}
    print(new_sets)
    #合并字典
    dict_a = {'a':1,'b':3}
    dict_b = {'c':2,'d':4}
    dict_all = {**dict_a,**dict_b}
    print(dict_all)
    #复制列表
    nums = [1,2,3,5]
    new_nums = nums[::]
    nums2 = nums
    nums =[3,4,5,6]
    print(new_nums)
    print(nums2)
    print(nums)
    #反转列表
    reverse_nums = [1,2,3,4,5,6]
    reverse_nums = reverse_nums[::-1]
    print(reverse_nums)
    #变量交换
    a,b = 1,2
    a,b = b,a
    print(a+":"+b)
if __name__ == '__main__':
     pythonic()