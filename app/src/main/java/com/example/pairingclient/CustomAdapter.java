package com.example.pairingclient;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<ListItem> listItems;
    private Boolean editable, favorites, PickUpMode;
    ListView listView;
    ViewHolder viewHolder;
    String ListType = "";

    CustomAdapter(Context context, List<ListItem> listItems, ListView listView) {
        this.context = context;
        this.listItems = listItems;
        this.listView = listView;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listItems.indexOf(getItem(i));
    }

    private class ViewHolder {
        public TextView textViewCourseInfo;
        public TextView textViewTaskInfo;
        public TextView textViewPairInfo;
        public TextView textViewAgreementStatus;
        public Button buttonViewAgree;
        public Button buttonViewDontAgree;
        public Button buttonViewFullInfo;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    public void deleteEvent(int ID) {
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).getId() == ID) {
                listItems.remove(i);
                return;
            }
        }
    }



    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        //final ViewHolder holder =null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (v == null) {
            v = mInflater.inflate(R.layout.list_item, null);
            final ViewHolder holder = new ViewHolder();
            viewHolder = holder;
            holder.textViewCourseInfo = (TextView) v.findViewById(R.id.TVCourseInfo);
            holder.textViewTaskInfo = (TextView) v.findViewById(R.id.TVTaskInfo);
            holder.textViewPairInfo = (TextView) v.findViewById(R.id.TVPairInfo);
            holder.textViewAgreementStatus = (TextView) v.findViewById(R.id.TVAgreementStatus);
            holder.buttonViewAgree = (Button) v.findViewById(R.id.btnAgreePair);
            holder.buttonViewDontAgree = (Button) v.findViewById(R.id.btnDontAgreePair);
            holder.buttonViewFullInfo = (Button) v.findViewById(R.id.btnPairFullInfo);
            //final Typeface tvFont = Typeface.createFromAsset(context.getAssets(), "fonts/newfont.otf");
            final Typeface tvFont = ResourcesCompat.getFont(context, R.font.newfont);
            holder.textViewCourseInfo.setTypeface(tvFont);
            holder.textViewTaskInfo.setTypeface(tvFont);
            holder.textViewPairInfo.setTypeface(tvFont);
            holder.textViewAgreementStatus.setTypeface(tvFont);
            holder.buttonViewAgree.setTypeface(tvFont);
            holder.buttonViewDontAgree.setTypeface(tvFont);
            holder.buttonViewFullInfo.setTypeface(tvFont);


            holder.buttonViewFullInfo.setVisibility(View.INVISIBLE);

            final ListItem listItem = listItems.get(i);
            String PairUser_Name = listItem.getPair_UN();

            holder.buttonViewFullInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Globals.itemDetails = listItem;
                    if (Globals.global_user_name.equals(listItem.getUsername()))
                        Globals.typePair = 0;
                    else if (Globals.global_user_name.equals(listItem.getPair_UN()))
                        Globals.typePair = 1;
                    Intent intent = new Intent(context, showPartner.class);
                    context.startActivity(intent);
                }
            });

            if (Globals.global_user_name.equals(listItem.getUsername())) {

                holder.textViewCourseInfo.setText(listItem.getCourse());
                holder.textViewTaskInfo.setText(listItem.getWorktype());
                holder.textViewPairInfo.setText(listItem.getPairName());

                if (listItem.getAgreed2() == -1) {
                    holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                    holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                    holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                } else if (listItem.getAgreed2() == 1) {
                    holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                    if (listItem.getAgreed1() == 1) {
                        holder.textViewAgreementStatus.setText("אתם שותפים");
                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                    } else if (listItem.getAgreed1() == -1) {
                        holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                    }
                } else if (listItem.getAgreed2() == 0) {
                    holder.textViewAgreementStatus.setText("");
                    if (listItem.getAgreed1() == 1) {
                        holder.textViewAgreementStatus.setText("ממתין להסכמה");
                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                    } else if (listItem.getAgreed1() == -1) {
                        holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                    }
                }
                holder.buttonViewAgree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Toast.makeText(context, "הסכמה התבצעה", Toast.LENGTH_LONG).show();
                                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                        listItem.setAgreed1(1);
                                        if (listItem.getAgreed2() == -1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                                            holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                            holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        } else if (listItem.getAgreed2() == 1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                                            if (listItem.getAgreed1() == 1) {
                                                holder.textViewAgreementStatus.setText("אתם שותפים");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                                            } else if (listItem.getAgreed1() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        } else if (listItem.getAgreed2() == 0) {
                                            holder.textViewAgreementStatus.setText("");
                                            if (listItem.getAgreed1() == 1) {
                                                holder.textViewAgreementStatus.setText("ממתין להסכמה");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                            } else if (listItem.getAgreed1() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        }
                                        //Intent intent = new Intent();
                                        //getActivity().startActivity(intent);
                                        //Intent intent = new Intent(AuthenticateUser.this, RegisterEventActivity.class);
                                        //AuthenticateUser.this.startActivity(intent);

                                        try {
                                            //if (AuthenticateUser.this != null)
                                            //hideSoftKeyboard(AuthenticateUser.this);
                                        } catch (Exception e) {

                                        }

                                    } else {
                                        Toast.makeText(context, "שליחה נכשלה", Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        AgreementReq AgreementRequest = new AgreementReq(listItem.getId(), 0, 1, "id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(AgreementRequest);

                    }
                });
                holder.buttonViewDontAgree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Toast.makeText(context, "הסכמה התבצעה", Toast.LENGTH_LONG).show();
                                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                        listItem.setAgreed1(-1);
                                        if (listItem.getAgreed2() == -1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                                            holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                            holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        } else if (listItem.getAgreed2() == 1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                                            if (listItem.getAgreed1() == 1) {
                                                holder.textViewAgreementStatus.setText("אתם שותפים");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                                            } else if (listItem.getAgreed2() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        } else if (listItem.getAgreed2() == 0) {
                                            holder.textViewAgreementStatus.setText("");
                                            if (listItem.getAgreed1() == 1) {
                                                holder.textViewAgreementStatus.setText("ממתין להסכמה");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                            } else if (listItem.getAgreed1() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        }
                                        //Intent intent = new Intent();
                                        //getActivity().startActivity(intent);
                                        //Intent intent = new Intent(AuthenticateUser.this, RegisterEventActivity.class);
                                        //AuthenticateUser.this.startActivity(intent);

                                        try {
                                            //if (AuthenticateUser.this != null)
                                            //hideSoftKeyboard(AuthenticateUser.this);
                                        } catch (Exception e) {

                                        }

                                    } else {
                                        Toast.makeText(context, "שליחה נכשלה", Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        AgreementReq AgreementRequest = new AgreementReq(listItem.getId(), 0, -1, "id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(AgreementRequest);
                    }
                });
            }
            else if (Globals.global_user_name.equals(listItem.getPair_UN())) {
                holder.textViewCourseInfo.setText(listItem.getCourse());
                holder.textViewTaskInfo.setText(listItem.getWorktype());
                holder.textViewPairInfo.setText(listItem.getName());
                if (listItem.getAgreed1() == -1) {
                    holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                    holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                    holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                } else if (listItem.getAgreed1() == 1) {
                    holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                    if (listItem.getAgreed2() == 1) {
                        holder.textViewAgreementStatus.setText("אתם שותפים");
                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                    } else if (listItem.getAgreed2() == -1) {
                        holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                    }
                } else if (listItem.getAgreed1() == 0) {
                    holder.textViewAgreementStatus.setText("");
                    if (listItem.getAgreed2() == 1) {
                        holder.textViewAgreementStatus.setText("ממתין להסכמה");
                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                    } else if (listItem.getAgreed2() == -1) {
                        holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                    }
                }
                holder.buttonViewAgree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Toast.makeText(context, "הסכמה התבצעה", Toast.LENGTH_LONG).show();
                                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                        listItem.setAgreed2(1);
                                        if (listItem.getAgreed1() == -1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                                            holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                            holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        } else if (listItem.getAgreed1() == 1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                                            if (listItem.getAgreed2() == 1) {
                                                holder.textViewAgreementStatus.setText("אתם שותפים");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                                            } else if (listItem.getAgreed2() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        } else if (listItem.getAgreed1() == 0) {
                                            holder.textViewAgreementStatus.setText("");
                                            if (listItem.getAgreed2() == 1) {
                                                holder.textViewAgreementStatus.setText("ממתין להסכמה");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                            } else if (listItem.getAgreed2() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        }
                                        //Intent intent = new Intent();
                                        //getActivity().startActivity(intent);
                                        //Intent intent = new Intent(AuthenticateUser.this, RegisterEventActivity.class);
                                        //AuthenticateUser.this.startActivity(intent);

                                        try {
                                            //if (AuthenticateUser.this != null)
                                            //hideSoftKeyboard(AuthenticateUser.this);
                                        } catch (Exception e) {

                                        }

                                    } else {
                                        Toast.makeText(context, "שליחה נכשלה", Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        AgreementReq AgreementRequest = new AgreementReq(listItem.getId(), 1, 1, "id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(AgreementRequest);

                    }
                });
                holder.buttonViewDontAgree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Toast.makeText(context, "הסכמה התבצעה", Toast.LENGTH_LONG).show();
                                        holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                        listItem.setAgreed2(-1);
                                        if (listItem.getAgreed1() == -1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך לא הסכים/ה");
                                            holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                            holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                        } else if (listItem.getAgreed1() == 1) {
                                            holder.textViewAgreementStatus.setText("שותפ/ה שלך מסכים/ה");
                                            if (listItem.getAgreed2() == 1) {
                                                holder.textViewAgreementStatus.setText("אתם שותפים");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewFullInfo.setVisibility(View.VISIBLE);
                                            } else if (listItem.getAgreed2() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        } else if (listItem.getAgreed1() == 0) {
                                            holder.textViewAgreementStatus.setText("");
                                            if (listItem.getAgreed2() == 1) {
                                                holder.textViewAgreementStatus.setText("ממתין להסכמה");
                                                holder.buttonViewDontAgree.setVisibility(View.INVISIBLE);
                                                holder.buttonViewAgree.setVisibility(View.INVISIBLE);
                                            } else if (listItem.getAgreed2() == -1) {
                                                holder.textViewAgreementStatus.setText("את/ה לא הסכמת");
                                            }
                                        }
                                        //Intent intent = new Intent();
                                        //getActivity().startActivity(intent);
                                        //Intent intent = new Intent(AuthenticateUser.this, RegisterEventActivity.class);
                                        //AuthenticateUser.this.startActivity(intent);

                                        try {
                                            //if (AuthenticateUser.this != null)
                                            //hideSoftKeyboard(AuthenticateUser.this);
                                        } catch (Exception e) {

                                        }

                                    } else {
                                        Toast.makeText(context, "שליחה נכשלה", Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        AgreementReq AgreementRequest = new AgreementReq(listItem.getId(), 1, -1, "id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(AgreementRequest);
                    }
                });
            }





/*
            CardView cardView = v.findViewById(R.id.CardViewListItem);
            ViewGroup.LayoutParams LPC = (ViewGroup.LayoutParams) cardView.getLayoutParams();
            LPC.width = (int) (LPC.width * Globals.scaleDP);
            LPC.height = (int) (LPC.height * Globals.scaleDP);
            cardView.setLayoutParams(LPC);
            final int childCount = cardView.getChildCount();
            float Scale169=1f;
            if(Globals.ActualWidth / Globals.ActualHeight > 9.0f /16.0f)
                Scale169=0.95f;
            for (int c = 0; c < childCount; c++) {
                View view = cardView.getChildAt(c);

                FrameLayout.LayoutParams LP = (FrameLayout.LayoutParams) view.getLayoutParams();
                FrameLayout.LayoutParams NewLP = new FrameLayout.LayoutParams(LP);


                NewLP.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
                        (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));
                if (NewLP.height > 0 && NewLP.width > 0) {
                    NewLP.width = (int) (LP.width * Globals.scaleDP);
                    NewLP.height = (int) (LP.height * Globals.scaleDP);
                }
                if (view instanceof Button) {
                    Button button = (Button) view;
                    float size = button.getTextSize();
                    button.setTextSize((button.getTextSize() * Scale169 * Globals.scaleDP * Globals.scaleS) / Globals.DP);
                } else if (view instanceof EditText) {
                    EditText editText = (EditText) view;
                    float size = editText.getTextSize();
                    editText.setTextSize((editText.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
                } else if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    float size = textView.getTextSize();
                    textView.setTextSize((textView.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
                } else if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    float height = imageView.getHeight();
                    float width = imageView.getWidth();

                }

                view.setLayoutParams(NewLP);


            }




*/







        }

        return v;
    }


}
