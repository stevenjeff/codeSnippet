import xlrd

file_path = r'.\test.xlsx'
workbook = xlrd.open_workbook(file_path)  # 打开excel工作簿
sheet = workbook.sheet_by_index(0)  # 选择第一张sheet
for row in range(sheet.nrows):  # 第一个for循环遍历所有行
    print()
    # for col in range(sheet.ncols):  # 第二个for循环遍历所有列，这样就找到某一个xy对应的元素，就可以打印出来
    #     print("%7s" % sheet.row(row)[col].value, '\t', end='')
    print('"%7s"' % sheet.row(row)[0].value, '', end=':')
    if sheet.row(row)[1].value == "string":
        print('""', end="")
    print('', end=",")

