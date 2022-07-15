import pandas as pd


class ModeTransformer:
    def __init__(self):
        pass

    def transform(self, rows):
        df = pd.DataFrame(rows)
        ret = df.mode().values[0]
        return ret.tolist()
