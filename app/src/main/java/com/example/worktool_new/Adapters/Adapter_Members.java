package com.example.worktool_new.Adapters;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.MemberModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.DebounceClickListener;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.EditSkills;
import com.example.worktool_new.Views.Activities.ImportCv;
import com.example.worktool_new.Views.Activities.MemberChangePassword;
import com.example.worktool_new.Views.Activities.MemberProfile;
import com.example.worktool_new.Views.Activities.addCer.AddCer;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class Adapter_Members extends RecyclerView.Adapter<Adapter_Members.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<MemberModel.Compte> datamodelArraylist;
    ondelete ondelete;
    /* access modifiers changed from: private */
    public ArrayList<MemberModel.Compte> orig;

    public interface ondelete {
        void ondelete(int i);
    }

    public Adapter_Members(Context context2, ArrayList<MemberModel.Compte> datamodelArraylist2, ondelete ondelete2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
        this.ondelete = ondelete2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_members, parent, false), this.ondelete);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TextView textView = holder.tvmemberName;
        textView.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        TextView textView2 = holder.tvMemberType;
        textView2.setText("[" + this.datamodelArraylist.get(position).getType() + "]");
        if (this.datamodelArraylist.get(position).getImage() != null) {
            if (!this.datamodelArraylist.get(position).getImage().isEmpty()) {
                Picasso.get().load(AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage()).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) holder.iv_MemberImage);
            } else {
                holder.iv_MemberImage.setImageResource(R.drawable.profileplaceholder);
            }
        }

        holder.ivActionMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v,datamodelArraylist, position);
            }
        });


       /* holder.ivActionMembers.setOnClickListener(new DebounceClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(context, holder.ivActionMembers);
                try {
                    Field[] fields = popup.getClass().getDeclaredFields();
                    int length = fields.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Field field = fields[i];
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);

                            Object menuPopupHelper = field.get(popup);

                            Class<?> classPopupHelper = null;
                            if (menuPopupHelper != null) {
                                classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            }
                            Method setForceIcons = null;
                            if (classPopupHelper != null) {
                                setForceIcons = classPopupHelper.getMethod("setForceShowIcon", new Class[]{Boolean.TYPE});
                            }
                            if (setForceIcons != null) {
                                setForceIcons.invoke(menuPopupHelper, new Object[]{false});
                            }
                        } else {
                            i++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Beneficiaire") || ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("jeune")) {
                    popup.getMenuInflater().inflate(R.menu.action_newmenu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            int i = item.getItemId();
                            if (i == R.id.modifier) {
                                if (((((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom()) != null && !((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom().isEmpty()) || !((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom().isEmpty()) {
                                    Intent intent = new Intent(Adapter_Members.this.context, MemberChangePassword.class);
                                    intent.putExtra("memberId", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte());
                                    intent.putExtra("memberName", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom());
                                    Adapter_Members.this.context.startActivity(intent);
                                }
                                return true;
                            } else if (i == R.id.importercv) {
                                Adapter_Members.this.context.startActivity(new Intent(context, ImportCv.class));
                                return true;
                            } else if (i == R.id.editercv) {
                                Adapter_Members.this.context.startActivity(new Intent(context, EditSkills.class));
                                return true;
                            } else if (i == R.id.ajoutercer) {
                                Intent intent2 = new Intent(Adapter_Members.this.context, AddCer.class);
                                intent2.putExtra("MemberId", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte());
                                Adapter_Members.this.context.startActivity(intent2);
                                return true;
                            } else if (i == R.id.supprimer) {
                                Adapter_Members.this.showdialog(Integer.parseInt(((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte()));
                                return true;
                            } else if (i != R.id.cancel) {
                                return onMenuItemClick(item);
                            } else {
                                popup.dismiss();
                                return true;
                            }
                        }
                    });
                    popup.show();
                }
                if (((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Parrain") || ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Professionnel")) {
                    popup.getMenuInflater().inflate(R.menu.action_newmenu2, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            int i = item.getItemId();
                            if (i == R.id.modifier) {
                                Intent intent = new Intent(Adapter_Members.this.context, MemberChangePassword.class);
                                intent.putExtra("memberName", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom());
                                Adapter_Members.this.context.startActivity(intent);
                                return true;
                            } else if (i == R.id.supprimer) {
                                Adapter_Members.this.showdialog(Integer.parseInt(((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte()));
                                return true;
                            } else if (i != R.id.cancel) {
                                return onMenuItemClick(item);
                            } else {
                                popup.dismiss();
                                return true;
                            }
                        }
                    });
                    popup.show();
                }
            }
        }));*/
    }
    private void showPopUp(View v, ArrayList<MemberModel.Compte> datamodelArraylist, final int position) {
        final PopupMenu popupMenu=new PopupMenu(context,v);
        if (((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Beneficiaire") || ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("jeune")) {
            popupMenu.getMenuInflater().inflate(R.menu.action_newmenu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    int i = item.getItemId();
                    if (i == R.id.modifier) {
                        if (((((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom()) != null && !((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom().isEmpty()) || !((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom().isEmpty()) {
                            Intent intent = new Intent(Adapter_Members.this.context, MemberChangePassword.class);
                            intent.putExtra("memberId", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte());
                            intent.putExtra("memberName", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom());
                            Adapter_Members.this.context.startActivity(intent);
                        }
                        return true;
                    } else if (i == R.id.importercv) {
                        Adapter_Members.this.context.startActivity(new Intent(context, ImportCv.class));
                        return true;
                    } else if (i == R.id.editercv) {
                        Adapter_Members.this.context.startActivity(new Intent(context, EditSkills.class));
                        return true;
                    } else if (i == R.id.ajoutercer) {
                        Intent intent2 = new Intent(Adapter_Members.this.context, AddCer.class);
                        intent2.putExtra("MemberId", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte());
                        Adapter_Members.this.context.startActivity(intent2);
                        return true;
                    } else if (i == R.id.supprimer) {
                        Adapter_Members.this.showdialog(Integer.parseInt(((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte()));
                        return true;
                    } else if (i != R.id.cancel) {
                        return onMenuItemClick(item);
                    } else {
                        popupMenu.dismiss();
                        return true;
                    }
                }
            });
            popupMenu.show();
        }
        if (((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Parrain") || ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getType().equals("Professionnel")) {
            popupMenu.getMenuInflater().inflate(R.menu.action_newmenu2, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    int i = item.getItemId();
                    if (i == R.id.modifier) {
                        Intent intent = new Intent(Adapter_Members.this.context, MemberChangePassword.class);
                        intent.putExtra("memberName", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getPrenom() + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getNom());
                        Adapter_Members.this.context.startActivity(intent);
                        return true;
                    } else if (i == R.id.supprimer) {
                        Adapter_Members.this.showdialog(Integer.parseInt(((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(position)).getIdCompte()));
                        return true;
                    } else if (i != R.id.cancel) {
                        return onMenuItemClick(item);
                    } else {
                        popupMenu.dismiss();
                        return true;
                    }
                }
            });
            popupMenu.show();
        }


    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults oReturn = new Filter.FilterResults();
                ArrayList<MemberModel.Compte> results = new ArrayList<>();
                if (Adapter_Members.this.orig == null) {
                    Adapter_Members adapter_Members = Adapter_Members.this;
                    ArrayList unused = adapter_Members.orig = adapter_Members.datamodelArraylist;
                }
                if (constraint != null) {
                    if (Adapter_Members.this.orig != null && Adapter_Members.this.orig.size() > 0) {
                        Iterator it = Adapter_Members.this.orig.iterator();
                        while (it.hasNext()) {
                            MemberModel.Compte g = (MemberModel.Compte) it.next();
                            if (g.getPrenom().toUpperCase().contains(constraint.toString().toUpperCase())) {
                                results.add(g);
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence constraint, Filter.FilterResults results) {
                ArrayList unused = Adapter_Members.this.datamodelArraylist = (ArrayList) results.values;
                Adapter_Members.this.notifyDataSetChanged();
            }
        };
    }

    public int getItemCount() {
        ArrayList<MemberModel.Compte> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void updateList(ArrayList<MemberModel.Compte> memberlist) {
        this.datamodelArraylist = memberlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout cardMember;
        RelativeLayout ivActionMembers;
        CircleImageView iv_MemberImage;
        ondelete ondeletel;
        TextView tvMemberType;
        TextView tvmemberName;

        public ViewHolder(View itemView, ondelete ondelete) {
            super(itemView);
            this.ivActionMembers = (RelativeLayout) itemView.findViewById(R.id.ivActionMembers);
            this.cardMember = (RelativeLayout) itemView.findViewById(R.id.cardMember);
            this.tvmemberName = (TextView) itemView.findViewById(R.id.tvmemberName);
            this.tvMemberType = (TextView) itemView.findViewById(R.id.tvMemberType);
            this.iv_MemberImage = (CircleImageView) itemView.findViewById(R.id.iv_MemberImage);
            this.ondeletel = ondelete;
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new DebounceClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Adapter_Members.this.context, MemberProfile.class);
                    intent.putExtra("memberProfileImage", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getImage());
                    intent.putExtra("memberName", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getCivilite() + " " + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getPrenom() + " " + ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getNom());
                    intent.putExtra(NotificationCompat.CATEGORY_STATUS, ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getCp());
                    intent.putExtra("memberEmail", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getEmail());
                    intent.putExtra("memberDOB", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getDate());
                    intent.putExtra("memebrTelephoneNumber", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getTelFixe());
                    intent.putExtra("memberMobileNumber", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getTelPort());
                    intent.putExtra("memberPostalcode", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getCp());
                    intent.putExtra("memeberAddress", ((MemberModel.Compte) Adapter_Members.this.datamodelArraylist.get(ViewHolder.this.getAdapterPosition())).getAdresse());
                    Adapter_Members.this.context.startActivity(intent);
                }
            }));
        }

        public void onClick(View v) {
        }
    }

    /* access modifiers changed from: private */
    public void showdialog(final int id) {
        final Dialog mDialog = new Dialog(this.context);
        mDialog.requestWindowFeature(1);
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.delete_member_layout);
        ((TextView) mDialog.findViewById(R.id.tv_no)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        ((TextView) mDialog.findViewById(R.id.tv_yes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Adapter_Members.this.ondelete.ondelete(id);
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }
}
