import matplotlib.pyplot as plt

x = []
y = []
for line in open('TempiNaive.txt', 'r'):
    columns = [i for i in line.split()]
    x.append(columns[0])
    y.append(int(columns[1]))

plt.title("Misurazioni")
plt.xlabel('Lunghezza stringa')
plt.ylabel('Tempo impiegato')
plt.yticks(y)
#plt.locator_params(axis='y', nbins=10)
#plt.locator_params(axis='x', nbins=10)

fig,ax=plt.subplots()
ax.xaxis.set_major_locator(plt.MaxNLocator(10))
ax.yaxis.set_major_locator(plt.MaxNLocator(10))

plt.plot(x, y)

plt.show()