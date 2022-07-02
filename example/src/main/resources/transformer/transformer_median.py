import pandas as pd


class MedianTransformer:
    def __init__(self):
        pass

    def transform(self, rows):
        df = pd.DataFrame(rows)
        ret = pd.DataFrame(data=df.median(axis=0)).transpose()
        return ret.values.tolist()
