import pandas as pd


class MaxTransformer:
    def __init__(self):
        pass

    def transform(self, rows):
        df = pd.DataFrame(rows)
        del df[df.keys()[0]]
        ret = pd.DataFrame(data=df.max(axis=0)).transpose()
        return ret.values.tolist()
