import math
import numpy as np

class NonNegativeDifferenceTransformer:
    def __init__(self):
        pass

    def transform(self, rows):
        arr = np.array(rows)
        timestamp = arr[:,0]
        data = arr[:,1]   
        length = len(data)
        res = []
        currData = None
        currTs = None
        for i in range(length):
            if data[i] != None and not np.isnan(data[i]):
                if currData != None:
                    res.append([timestamp[i],abs((data[i] - currData) / (timestamp[i] - currTs))])
                currData = data[i]
                currTs = timestamp[i]
        if len(res) == 0:
            res.append(np.NaN)
        return res

